package nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class  UDPClient  extends  Thread  { 
	public static void main(String[] args) {
		new UDPClient().start();
	}
     public  void  run () { 
         DatagramChannel channel =  null ; 
         Selector selector =  null ; 
         try  { 
             channel = DatagramChannel.open () ; 
             channel.configureBlocking ( false ) ; 
             SocketAddress sa =  new  InetSocketAddress ( "localhost" ,  5057 ) ; 
             channel.connect ( sa ) ; 
         }  catch  ( Exception e ) { 
             e.printStackTrace () ; 
         } 

         try  { 
             selector = Selector.open () ; 
             channel.register ( selector, SelectionKey.OP_READ ) ; 
             channel.write ( Charset.defaultCharset () .encode ( "Tell me your time" )) ; 
         }  catch  ( Exception e ) { 
             e.printStackTrace () ; 
         } 
         
         ByteBuffer byteBuffer = ByteBuffer.allocate ( 100 ) ; 
         while  ( true ) { 
             try  { 
                 int  eventsCount = selector.select () ; 
                 if  ( eventsCount >  0 ) { 
                     Set selectedKeys = selector.selectedKeys () ; 
                     Iterator iterator = selectedKeys.iterator () ; 
                     while  ( iterator.hasNext ()) { 
                         SelectionKey sk =  ( SelectionKey )  iterator.next () ; 
                         iterator.remove () ; 
                         if  ( sk.isReadable ()) { 
                             DatagramChannel datagramChannel =  ( DatagramChannel )  sk 
                                     .channel () ; 
                             datagramChannel.read ( byteBuffer ) ; 
                             byteBuffer.flip () ; 
                             
                             //TODO ������ת��ΪRUDP��Ϣ������RUDPЭ�鴦���������� 
                             
                             System.out.println ( Charset.defaultCharset () .decode ( 
                                     byteBuffer ) .toString ()) ; 
                             byteBuffer.clear () ; 
                             datagramChannel.write ( Charset.defaultCharset () 
                                     .encode ( "Tell me your time" )) ; 
                         } 
                     } 
                 } 
             }  catch  ( Exception e ) { 
                 e.printStackTrace () ; 
             } 
         } 

     } 
} 
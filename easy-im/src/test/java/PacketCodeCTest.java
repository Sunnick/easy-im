import com.sunnick.easyim.packet.LoginRequestPacket;
import com.sunnick.easyim.protocol.JsonSerializer;
import com.sunnick.easyim.protocol.Packet;
import com.sunnick.easyim.protocol.PacketCodeC;
import com.sunnick.easyim.protocol.Serializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sunnick on 2019/1/13/013.
 */
public class PacketCodeCTest {

    @Test
    public void enconde(){
        Serializer serializer = new JsonSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUserName("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = PacketCodeC.getInstance();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }

}

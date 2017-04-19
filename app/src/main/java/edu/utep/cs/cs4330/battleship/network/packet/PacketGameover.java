// Author: Jose Perez <josegperez@mail.com> and Diego Reynoso
package edu.utep.cs.cs4330.battleship.network.packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PacketGameover extends Packet {
    public boolean isWin;

    public PacketGameover(ObjectInputStream input) throws IOException{
        super(PacketID.GAMEOVER);
        isWin = input.readBoolean();
    }

    public PacketGameover(boolean isWin){
        super(PacketID.GAMEOVER);
        this.isWin = isWin;
    }

    @Override
    public void sendPacket(ObjectOutputStream output) throws IOException {
        output.writeBoolean(isWin);
    }
}

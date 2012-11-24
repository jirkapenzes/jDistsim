package jDistsim.utils.common;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Author: Jirka Pénzeš
 * Date: 24.11.12
 * Time: 15:52
 */
public class TransferableObject<Type> implements Transferable {

    private DataFlavor dataFlavor;
    private DataFlavor[] supportedFlavors;
    private Type transferableObject;

    public TransferableObject(Type transferableObject) {
        dataFlavor = new DataFlavor(transferableObject.getClass(), "Transferable object " + transferableObject.getClass().toString());
        supportedFlavors = new DataFlavor[]{dataFlavor};
        this.transferableObject = transferableObject;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        if (flavor.equals(dataFlavor) || flavor.equals(DataFlavor.stringFlavor))
            return true;
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.equals(dataFlavor))
            return transferableObject;
        else if (flavor.equals(DataFlavor.stringFlavor))
            return transferableObject.toString();
        else
            throw new UnsupportedFlavorException(flavor);
    }
}

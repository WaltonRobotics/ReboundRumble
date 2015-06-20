package edu.team2974.util;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;

/**
 *
 * @author Chris
 */
public class DFileReader extends StaticWidget {

    public static final String NAME = "DFileReader";
    @Override
    public void init() {
        ButtonPanel bp = new ButtonPanel();
        bp.getButton().setText("THIS IS MY BUTON TEXT");
        this.add(bp);
        //
    }

    @Override
    public void propertyChanged(Property prprt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
//     @Override
//    protected void paintComponent(Graphics g) {
//     }
    
}

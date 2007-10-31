/**
 *  Copyright (C) 2002-2007  The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */


package net.sf.freecol.common.model;



import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


public class StanceTradeItem extends TradeItem {
    
    /**
     * The stance between source and destination.
     */
    private int stance;
        
    /**
     * Creates a new <code>StanceTradeItem</code> instance.
     *
     * @param game a <code>Game</code> value
     * @param source a <code>Player</code> value
     * @param destination a <code>Player</code> value
     * @param stance an <code>int</code> value
     */
    public StanceTradeItem(Game game, Player source, Player destination, int stance) {
        super(game, "tradeItem.stance", source, destination);
        this.stance = stance;
    }

    /**
     * Creates a new <code>StanceTradeItem</code> instance.
     *
     * @param game a <code>Game</code> value
     * @param in a <code>XMLStreamReader</code> value
     */
    public StanceTradeItem(Game game, XMLStreamReader in) throws XMLStreamException {
        super(game, in);
        readFromXMLImpl(in);
    }

    /**
     * Get the <code>Stance</code> value.
     *
     * @return an <code>int</code> value
     */
    public final int getStance() {
        return stance;
    }

    /**
     * Set the <code>Stance</code> value.
     *
     * @param newStance The new Stance value.
     */
    public final void setStance(final int newStance) {
        this.stance = newStance;
    }

    /**
     * Returns whether this TradeItem is valid.
     *
     * @return a <code>boolean</code> value
     */
    public boolean isValid() {
        return (stance == Player.WAR ||
                stance == Player.CEASE_FIRE ||
                stance == Player.PEACE ||
                stance == Player.ALLIANCE);
    }

    /**
     * Returns whether this TradeItem must be unique. This is true for
     * the StanceTradeItem and the GoldTradeItem, and false for all
     * others.
     *
     * @return a <code>boolean</code> value
     */
    public boolean isUnique() {
        return true;
    }

    /**
     * Concludes the trade.
     *
     */
    public void makeTrade() {
        getSource().setStance(getDestination(), stance);
        getDestination().setStance(getSource(), stance);
    }


    /**
     * Initialize this object from an XML-representation of this object.
     * @param in The input stream with the XML.
     * @throws XMLStreamException if a problem was encountered
     *      during parsing.
     */
    protected void readFromXMLImpl(XMLStreamReader in) throws XMLStreamException {
        super.readFromXMLImpl(in);
        this.stance = Integer.parseInt(in.getAttributeValue(null, "stance"));
        in.nextTag();
    }

    /**
     * This method writes an XML-representation of this object to
     * the given stream.
     * 
     * @param out The target stream.
     * @throws XMLStreamException if there are any problems writing
     *      to the stream.
     */
    public void toXMLImpl(XMLStreamWriter out) throws XMLStreamException {
        out.writeStartElement(getXMLElementTagName());
        super.toXML(out);
        out.writeAttribute("stance", Integer.toString(this.stance));
        out.writeEndElement();
    }
    
    /**
     * Gets the tag name of the root element representing this object.
     * @return "goods".
     */
    public static String getXMLElementTagName() {
        return "stanceTradeItem";
    }

}


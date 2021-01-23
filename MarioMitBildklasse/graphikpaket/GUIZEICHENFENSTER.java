package graphikpaket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasse GUIZEICHENFENSTER.
 * 
 * @author (K. Horlebein) 
 * @version (V1_20130402)
 */
public class GUIZEICHENFENSTER extends ZEICHENFENSTER implements ActionListener
{
    private JPanel BedienPanelNord,BedienPanelNordLinks,BedienPanelNordMitte,BedienPanelNordRechts,BedienPanelNordUnten;
    private JPanel BedienPanelWestOben,BedienPanelWest,BedienPanelWestMitte,BedienPanelWestRechts,BedienPanelWestUnten;
    private JPanel BedienPanelOstOben,BedienPanelOstLinks,BedienPanelOstMitte,BedienPanelOst,BedienPanelOstUnten;
    private JPanel BedienPanelSuedOben,BedienPanelSuedLinks,BedienPanelSuedMitte,BedienPanelSuedRechts,BedienPanelSued;
 //JPanel sind Behaelter fuer Bedienelemente wie Buttons, Ankreuzfelder etc.
    private JPanel FensterElementContainerOben=new JPanel();
    private JPanel FensterElementContainerRechts=new JPanel();
    private JPanel FensterElementContainerUnten=new JPanel();
    private JPanel FensterElementContainerLinks=new JPanel();
    public JButton[] Knopfliste;
    public JLabel[] Labelliste;
    public JLabel HinweisOben = new JLabel("");
    public JLabel HinweisLinks = new JLabel(""); 
    public JLabel HinweisMitte=new JLabel("");
    public JLabel HinweisRechts = new JLabel("");
    public JLabel HinweisUnten = new JLabel("");
    public String EreignisZustand;
    public GUIZEICHENFENSTER()
    {
       FensterElementContainerOben.setLayout(new BorderLayout());
       FensterElementContainerLinks.setLayout(new BorderLayout());
       FensterElementContainerRechts.setLayout(new BorderLayout());
       FensterElementContainerUnten.setLayout(new BorderLayout());
       
       BedienPanelNord = new JPanel();
       BedienPanelNord.setLayout(new BoxLayout(BedienPanelNord,BoxLayout.X_AXIS));
       BedienPanelNordLinks = new JPanel();
       BedienPanelNordLinks.setLayout(new BoxLayout(BedienPanelNordLinks,BoxLayout.Y_AXIS));
       BedienPanelNordMitte = new JPanel();
       BedienPanelNordMitte.setLayout(new BoxLayout(BedienPanelNordMitte,BoxLayout.X_AXIS));
       BedienPanelNordRechts = new JPanel();
       BedienPanelNordRechts.setLayout(new BoxLayout(BedienPanelNordRechts,BoxLayout.Y_AXIS));
       BedienPanelNordUnten = new JPanel();
       BedienPanelNordUnten.setLayout(new BoxLayout(BedienPanelNordUnten,BoxLayout.X_AXIS));
       
       BedienPanelWestOben = new JPanel();
       BedienPanelWestOben.setLayout(new BoxLayout(BedienPanelWestOben,BoxLayout.X_AXIS));
       BedienPanelWest = new JPanel();
       BedienPanelWest.setLayout(new BoxLayout(BedienPanelWest,BoxLayout.Y_AXIS));
       BedienPanelWestMitte = new JPanel();
       BedienPanelWestMitte.setLayout(new BoxLayout(BedienPanelWestMitte,BoxLayout.Y_AXIS));
       BedienPanelWestRechts = new JPanel();
       BedienPanelWestRechts.setLayout(new BoxLayout(BedienPanelWestRechts,BoxLayout.Y_AXIS));
       BedienPanelWestUnten = new JPanel();
       BedienPanelWestUnten.setLayout(new BoxLayout(BedienPanelWestUnten,BoxLayout.X_AXIS));

       BedienPanelOstOben = new JPanel();
       BedienPanelOstOben.setLayout(new BoxLayout(BedienPanelOstOben,BoxLayout.X_AXIS));
       BedienPanelOstLinks = new JPanel();
       BedienPanelOstLinks.setLayout(new BoxLayout(BedienPanelOstLinks,BoxLayout.Y_AXIS));
       BedienPanelOstMitte = new JPanel();
       BedienPanelOstMitte.setLayout(new BoxLayout(BedienPanelOstMitte,BoxLayout.Y_AXIS));
       BedienPanelOst = new JPanel();
       BedienPanelOst.setLayout(new BoxLayout(BedienPanelOst,BoxLayout.Y_AXIS));
       BedienPanelOstUnten = new JPanel();
       BedienPanelOstUnten.setLayout(new BoxLayout(BedienPanelOstUnten,BoxLayout.X_AXIS));
       
       BedienPanelSuedOben = new JPanel();
       BedienPanelSuedOben.setLayout(new BoxLayout(BedienPanelSuedOben,BoxLayout.X_AXIS));
       BedienPanelSuedLinks = new JPanel();
       BedienPanelSuedLinks.setLayout(new BoxLayout(BedienPanelSuedLinks,BoxLayout.Y_AXIS));  
       BedienPanelSuedMitte = new JPanel();
       BedienPanelSuedMitte.setLayout(new BoxLayout(BedienPanelSuedMitte,BoxLayout.X_AXIS));
       BedienPanelSuedRechts = new JPanel();
       BedienPanelSuedRechts.setLayout(new BoxLayout(BedienPanelSuedRechts,BoxLayout.Y_AXIS)); 
       BedienPanelSued = new JPanel();
       BedienPanelSued.setLayout(new BoxLayout(BedienPanelSued,BoxLayout.X_AXIS));
       
       FensterElementContainerOben.add(BedienPanelNord,BorderLayout.NORTH);
       FensterElementContainerOben.add(BedienPanelNordLinks,BorderLayout.WEST);
       FensterElementContainerOben.add(BedienPanelNordMitte,BorderLayout.CENTER);
       FensterElementContainerOben.add(BedienPanelNordRechts,BorderLayout.EAST);
       FensterElementContainerOben.add(BedienPanelNordUnten,BorderLayout.SOUTH);

       FensterElementContainerLinks.add(BedienPanelWestOben,BorderLayout.NORTH);
       FensterElementContainerLinks.add(BedienPanelWest,BorderLayout.WEST);
       FensterElementContainerLinks.add(BedienPanelWestMitte,BorderLayout.CENTER);
       FensterElementContainerLinks.add(BedienPanelWestRechts,BorderLayout.EAST);
       FensterElementContainerLinks.add(BedienPanelWestUnten,BorderLayout.SOUTH);
 
       FensterElementContainerRechts.add(BedienPanelOstOben,BorderLayout.NORTH);
       FensterElementContainerRechts.add(BedienPanelOstLinks,BorderLayout.WEST);
       FensterElementContainerRechts.add(BedienPanelOstMitte,BorderLayout.CENTER);
       FensterElementContainerRechts.add(BedienPanelOst,BorderLayout.EAST);
       FensterElementContainerRechts.add(BedienPanelOstUnten,BorderLayout.SOUTH);
       
       FensterElementContainerUnten.add(BedienPanelSuedOben,BorderLayout.NORTH);
       FensterElementContainerUnten.add(BedienPanelSuedLinks,BorderLayout.WEST);
       FensterElementContainerUnten.add(BedienPanelSuedMitte,BorderLayout.CENTER);
       FensterElementContainerUnten.add(BedienPanelSuedRechts,BorderLayout.EAST);
       FensterElementContainerUnten.add(BedienPanelSued,BorderLayout.SOUTH);    

       getContentPane().add(FensterElementContainerOben,BorderLayout.NORTH);
       getContentPane().add(FensterElementContainerLinks,BorderLayout.WEST);
       getContentPane().add(FensterElementContainerRechts,BorderLayout.EAST);
       getContentPane().add(FensterElementContainerUnten,BorderLayout.SOUTH);
       
       fuegeHinzuKomponente(HinweisOben,"oben");
       fuegeHinzuKomponente(HinweisLinks,"links");
       fuegeHinzuKomponente(HinweisMitte,"untenOben");
       fuegeHinzuKomponente(HinweisRechts,"rechts");
       fuegeHinzuKomponente(HinweisUnten,"unten");
       
       EreignisZustand = "unberuehrt";
    }
    public void erzeugeKnopf(int KnopfNummer,String Beschriftung)
    {
        Knopfliste[KnopfNummer] = new JButton(Beschriftung);
    }
    public void erzeugeKnoepfe(int KnopfAnzahl)
    {
        Knopfliste = new JButton[KnopfAnzahl];
        for (int i=0; i<KnopfAnzahl;i++)
        {
            Knopfliste[i]=new JButton("Knopf"+i);
            benenneKnopf(Knopfliste[i],"Knopf"+i);
        }
    }
    public void beschrifteKnopf(JButton Knopf, String neuerText)
    {
        Knopf.setText(neuerText);
    }
    public void benenneKnopf(JButton Knopf, String neuerName)
    {
        Knopf.setName(neuerName);
    }
    public void erzeugeTextLabel (int LabelNummer,String Beschriftung)
    {
        Labelliste[LabelNummer] = new JLabel(Beschriftung);
    }
    public void erzeugeTextLabels(int LabelAnzahl)
    {
        Labelliste = new JLabel[LabelAnzahl];
        for (int i=0; i<LabelAnzahl;i++)
        {
            Labelliste[i]=new JLabel(""+i);
        }
    }
    public void beschrifteTextLabel(JLabel TextLabel, String neuerText)
    {
        TextLabel.setText(neuerText);
    }
    /**
     * Fuegt ein weiteres Steuerungselement in die rechte Steuerungsleiste ein.
     * @param  element  Das einzufuegende Steuerungselement muss aus JComponent abgeleitet
     * sein. z. B. JButton, JComboBox. 
     */
    public void fuegeHinzuKomponente(JComponent element, String position)
    {
        switch (position)
        {
            case "oben": BedienPanelNord.add(element);break;
            case "obenLinks": BedienPanelNordLinks.add(element);break; 
            case "obenMitte": BedienPanelNordMitte.add(element);break;
            case "obenRechts": BedienPanelNordRechts.add(element);break;
            case "obenUnten": BedienPanelNordUnten.add(element);break;
            case "linksOben": BedienPanelWestOben.add(element);break;
            case "links": BedienPanelWest.add(element);break;
            case "linksMitte": BedienPanelWestMitte.add(element);break;
            case "linksRechts": BedienPanelWestRechts.add(element);break;
            case "linksUnten": BedienPanelWestUnten.add(element);break;
            case "rechtsOben": BedienPanelOstOben.add(element);break;
            case "rechtsLinks": BedienPanelOstLinks.add(element);break;
            case "rechtsMitte": BedienPanelOstMitte.add(element);break;
            case "rechts": BedienPanelOst.add(element);break;
            case "rechtsUnten": BedienPanelOstUnten.add(element);break;
            case "untenOben": BedienPanelSuedOben.add(element);break;
            case "untenLinks": BedienPanelSuedLinks.add(element);break;
            case "untenMitte": BedienPanelSuedMitte.add(element);break;
            case "untenRechts": BedienPanelSuedRechts.add(element);break;
            case "unten": BedienPanelSued.add(element);break;
            default: BedienPanelSued.add(element);break;
        }
        pack();
    }
    public void fuegeAktionListenerZuKnopf(JButton Knopf)
    {
        Knopf.addActionListener(this);
    }
    public void fuegeHinzuAktionListenerZuKnoepfenAusListe(int vonElement,int bisElement)
    {
        int obergrenze;
        if(bisElement>Knopfliste.length){obergrenze=Knopfliste.length;}else{obergrenze=bisElement;}
        for (int i=vonElement;i<obergrenze;i++)
        {
            Knopfliste[i].addActionListener(this);
        }
    }
    public void fuegeHinzuKnoepfeAusListe(int vonElement, int bisElement,String Position)
    {
        int obergrenze;
        if(bisElement>Knopfliste.length){obergrenze=Knopfliste.length;}else{obergrenze=bisElement;}
        for (int i=vonElement;i<obergrenze;i++)
        {
            fuegeHinzuKomponente(Knopfliste[i],Position);
        }
    }
    public void fuegeHinzuLabelAusListe(int vonElement, int bisElement,String Position)
    {
        int obergrenze;
        if(bisElement>Labelliste.length){obergrenze=Labelliste.length;}else{obergrenze=bisElement;}
        for (int i=vonElement;i<obergrenze;i++)
        {
            Labelliste[i].setText(""+i+""+Labelliste.length);
            fuegeHinzuKomponente(Labelliste[i],Position);
        }
    }
    public void actionPerformed (ActionEvent ae)
    {
        // Die Quelle wird mit getSource() abgefragt und mit den
        // Buttons abgeglichen. Wenn die Quelle des ActionEvents einer
        // der Buttons ist, wird der Text dem JLabel entsprechend geänder
        EreignisZustand=((JButton)ae.getSource()).getName();
    }
//     private void auswertenKnopfdruck(String gedrueckterKnopf)
//     {
//         switch(gedrueckterKnopf)
//         {
//             case "Knopf0":
//             Hinweis.setText("Button 0 wurde betätigt");
//             break;
//             case "Knopf1":
//             Hinweis.setText("Button 1 wurde betätigt");
//             break;
//             default:
//             Hinweis.setText("-"+gedrueckterKnopf+" wurde betätigt");
//             break;
//         }
//     }
}
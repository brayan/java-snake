import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.MouseEvent;

import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Snake extends JFrame implements ActionListener, FocusListener, ItemListener, KeyListener, MouseListener
{
	ActionListener acao;
	
	ButtonGroup bgNiveis;
	
	ImageIcon backTelaInicial;
	ImageIcon backTelaNivel;
	ImageIcon botaoContinuar;
	ImageIcon botaoContinuar2;
	ImageIcon botaoContinuar3;
	ImageIcon backIndiceFacil;
	ImageIcon backIndiceNormal;
	ImageIcon backIndiceDificil;
	ImageIcon CabecaSnakeDireita;
	ImageIcon CabecaSnakeEsquerda;
	ImageIcon CabecaSnakeCimaDireita;
	ImageIcon CabecaSnakeCimaEsquerda;
	ImageIcon CabecaSnakeBaixoDireita;
	ImageIcon CabecaSnakeBaixoEsquerda;
	ImageIcon caldaSnake;
	ImageIcon[] comida;
	
	JButton btJogar;
	
	JLabel lbBackTelaInicial;
	JLabel lbBackTelaNivel;
	JLabel lbBotaoContinuar;
	JLabel lbSnake;
	JLabel lbComida;
	JLabel lbTempoJogo;
	JLabel lbPontos;
	JLabel[] lbCaldaSnake;
	
	JPanel pnNiveis;
	JPanel pnTelaInicial;
	JPanel pnTelaPrincipal;
	JPanel pnTelaFinal;
	
	JMenuBar barraMenu;
	
	JMenu mOpcoes;
	JMenu mAjuda;
	
	JMenuItem miComandos;
	JMenuItem miNovoJogo;
	JMenuItem miSair;
	JMenuItem miSobre;
	
	JRadioButton rbFacil;
	JRadioButton rbNormal;
	JRadioButton rbDificil;
	

	
	Random random;
	
	Timer tempo;
	
	KeyEvent direcaoSnake;
	
	int tipoComida = 0;
	int deciIndice = 0;
	int vezesExecutado = 0;
	int num = 0;
	int velocidadeTimer = 1000;
	int pontos = 1;
	int ultimoMovimento = 0;
	
	int posxSnake = 10;
	int posySnake = 20;
	
	int posxComida = 60;
	int posyComida = 40;
	
	int moverX = 0;
	int moverY = 0;
	
	int ii = 0;
	
	int[] posxCorpo = new int[100];
	int[] posyCorpo = new int[100];
	
	String cima = "Cima";
	String direita = "Direita";
	String esquerda = "Esquerda";
	String baixo = "Baixo";
	String nivel = "";
	
	public Snake()
	{
        random = new Random();   
            	
            acao = new ActionListener() {   
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {   
            
                num++;
                
                posxCorpo[1] = posxSnake;
               	posyCorpo[1] = posySnake;
                
                if(ii == 1)
                {
               		posxSnake += (10);
               	
                }
               	else if(ii == 2)
               	{
               		posxSnake -= (10);
               		
               	}
               	else if(ii == 3)
               	{
               		posySnake += (10);
               	
               	}
               	else if(ii == 4)
               	{
               		posySnake -= (10);
               		
               	}
               	
               	lbSnake.setBounds(posxSnake, posySnake, 30, 30);
               	
          
               	for(int c = 1; c <= pontos; c++)
               	{
               		if(c != 1)
               		{
               			if(ii == 1)
               			{
               		
               				posxCorpo[c] = posxCorpo[c - 1] - 25;
               				posyCorpo[c] = posyCorpo[c - 1];
               			}
               			else if(ii == 2)
               			{
               				posxCorpo[c] = posxCorpo[c - 1] + 25;
               				posyCorpo[c] = posyCorpo[c - 1];
               			}
               			else if(ii == 3)
               			{
               		
               				posxCorpo[c] = posxCorpo[c - 1];
               				posyCorpo[c] = posyCorpo[c - 1] - 25;
               			}
               			else if(ii == 4)
               			{
               				posxCorpo[c] = posxCorpo[c - 1];
               				posyCorpo[c] = posyCorpo[c - 1] + 25;
               			}
               				
               			
               			
               		}
               		
               		lbCaldaSnake[c].setBounds(posxCorpo[c], posyCorpo[c], 26, 26);
               	}
               	
               	
               	//System.out.println("posxComida: "+posxComida+"\nposyComida: "+posyComida+"\nposxSnake: "+posxSnake+"\nposySnake: "+posySnake);	
               	
               	if((posxSnake >= posxComida - 45 && posxSnake <= posxComida + 20) && (posySnake >= posyComida - 45 && posySnake <= posyComida + 20))
				{
					
					lbPontos.setText(""+pontos++);
					lbCaldaSnake[pontos] = new JLabel(caldaSnake);
					pnTelaPrincipal.add(lbCaldaSnake[pontos]);
					
					
					posxComida = random.nextInt(500) + 10;
					posyComida = random.nextInt(300) + 10;
					
					tipoComida = random.nextInt(24);
					lbComida.setIcon(comida[tipoComida]);
				
					lbComida.setBounds(posxComida, posyComida, 32, 30);
					
				}
				
				
		
			
		if(posxSnake >= 560 || posxSnake <= -1 || posySnake >=310 || posySnake <= -31)
		{
		
			JOptionPane.showMessageDialog(null, "Você perdeu");
			tempo.stop();
		}  	
		   } 
        };
		
		configuracoesFrame();
		configuracoesMenu();
		configuracoesImagem();
		configuracoesLabel();
		configuracoesButtons();
		configuracoesRadioButton();
		configuracoesPanel();
		
		getContentPane().add(pnTelaInicial);
	
		

		setVisible(true);
	}
	
	public void configuracoesFrame()
	{
		Dimension dimencoes = Toolkit.getDefaultToolkit().getScreenSize();
		
		int posX = (int) ((dimencoes.width / 2) - 300);
        int posY = (int) ((dimencoes.height / 2) - 300);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(posX, posY);
		setResizable(false);
		setSize(600, 400);
		setTitle("Snake");
		
	}
	
	public void configuracoesButtons()
	{
		btJogar = new JButton("Jogar");
		btJogar.addActionListener(this);
		btJogar.setBounds(10, 300, 100, 40);
	}
	
	public void configuracoesImagem()
	{
		backTelaInicial = new ImageIcon(getClass().getResource("imagens/Backgrounds/BackgroundPrincipal.png"));
		backTelaNivel = new ImageIcon(getClass().getResource("imagens/Backgrounds/BackgroundNivel1.jpg"));
		botaoContinuar = new ImageIcon(getClass().getResource("imagens/Botoes/BotaoContinuar1.png"));
		botaoContinuar2 = new ImageIcon(getClass().getResource("imagens/Botoes/BotaoContinuar2.png"));
		botaoContinuar3 = new ImageIcon(getClass().getResource("imagens/Botoes/BotaoContinuar3.png"));
		backIndiceFacil = new ImageIcon(getClass().getResource("imagens/Backgrounds/BackgroundNivelFacil.jpg"));
		backIndiceNormal = new ImageIcon(getClass().getResource("imagens/Backgrounds/BackgroundNivelNormal.jpg"));
		backIndiceDificil = new ImageIcon(getClass().getResource("imagens/Backgrounds/BackgroundNivelDificil.jpg"));
		

		
		comida = new ImageIcon[25];
		
		comida[0] = new ImageIcon(getClass().getResource("imagens/Comida/Abacaxi.png"));
		comida[1] = new ImageIcon(getClass().getResource("imagens/Comida/Amora.png"));
		comida[2] = new ImageIcon(getClass().getResource("imagens/Comida/Banana.png"));
		comida[3] = new ImageIcon(getClass().getResource("imagens/Comida/CachorroQuente.png"));
		comida[4] = new ImageIcon(getClass().getResource("imagens/Comida/Cerveja.png"));
		comida[5] = new ImageIcon(getClass().getResource("imagens/Comida/Hamburger.png"));
		comida[6] = new ImageIcon(getClass().getResource("imagens/Comida/Laranja1.png"));
		comida[7] = new ImageIcon(getClass().getResource("imagens/Comida/Laranja2.png"));
		comida[8] = new ImageIcon(getClass().getResource("imagens/Comida/Limao.png"));
		comida[9] = new ImageIcon(getClass().getResource("imagens/Comida/Maca1.png"));
		comida[10] = new ImageIcon(getClass().getResource("imagens/Comida/Maca2.png"));
		comida[11] = new ImageIcon(getClass().getResource("imagens/Comida/MacaVerde.png"));
		comida[12] = new ImageIcon(getClass().getResource("imagens/Comida/Mamao.png"));
		comida[13] = new ImageIcon(getClass().getResource("imagens/Comida/Melancia1.png"));
		comida[14] = new ImageIcon(getClass().getResource("imagens/Comida/Melancia2.png"));
		comida[15] = new ImageIcon(getClass().getResource("imagens/Comida/Melao.png"));
		comida[16] = new ImageIcon(getClass().getResource("imagens/Comida/Morango1.png"));
		comida[17] = new ImageIcon(getClass().getResource("imagens/Comida/Morango2.png"));
		comida[18] = new ImageIcon(getClass().getResource("imagens/Comida/Ovo.png"));
		comida[19] = new ImageIcon(getClass().getResource("imagens/Comida/Pao.png"));
		comida[20] = new ImageIcon(getClass().getResource("imagens/Comida/PaoGeleia.png"));
		comida[21] = new ImageIcon(getClass().getResource("imagens/Comida/Picole.png"));
		comida[22] = new ImageIcon(getClass().getResource("imagens/Comida/Queijo.png"));
		comida[23] = new ImageIcon(getClass().getResource("imagens/Comida/Sorvete.png"));
		comida[24] = new ImageIcon(getClass().getResource("imagens/Comida/Uva.png"));
	}
	
	public void configuracoesMenu()
	{
		
		barraMenu = new JMenuBar();
		mOpcoes = new JMenu("Opções");
		mAjuda = new JMenu("Ajuda");
		miNovoJogo = new JMenuItem("Novo Jogo");
		miComandos = new JMenuItem("Comandos");
		miSair = new JMenuItem("Sair");
		miSobre = new JMenuItem("Sobre");		
		
		setJMenuBar(barraMenu);
		
		barraMenu.add(mOpcoes);
		barraMenu.add(mAjuda);
		
		mOpcoes.add(miNovoJogo);
		mOpcoes.addSeparator();
		mOpcoes.add(miSair);
		
		mAjuda.add(miComandos);
		mAjuda.add(miSobre);
		
		mOpcoes.setMnemonic(KeyEvent.VK_O);
		mAjuda.setMnemonic(KeyEvent.VK_A);
		miNovoJogo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		miSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		
		miNovoJogo.addActionListener(this);
		miComandos.addActionListener(this);
		miSobre.addActionListener(this);
		miSair.addActionListener(this);
		
		mOpcoes.addFocusListener(this);
	}
	
	public void configuracoesPanel()
	{ 
		pnTelaInicial = new JPanel();
		pnTelaInicial.setLayout(null);
		pnTelaInicial.add(lbBotaoContinuar);
		pnTelaInicial.add(lbBackTelaInicial);
		pnTelaInicial.setVisible(true);
		
		pnNiveis = new JPanel();
		pnNiveis.setLayout(null);
		pnNiveis.add(rbFacil);
		pnNiveis.add(rbNormal);
		pnNiveis.add(rbDificil);
		pnNiveis.add(btJogar);
		pnNiveis.add(lbBackTelaNivel);
		
		lbBackTelaNivel.setBounds(0, 0, 600, 400);
		pnNiveis.setVisible(true);
		
		pnTelaPrincipal = new JPanel();
		pnTelaPrincipal.setLayout(null);
	
		pnTelaPrincipal.add(lbSnake);
		pnTelaPrincipal.add(lbComida);
		pnTelaPrincipal.add(lbTempoJogo);
		pnTelaPrincipal.add(lbPontos);
		
		pnTelaPrincipal.addKeyListener(this);
		
		
		
		pnTelaFinal = new JPanel();
		pnTelaFinal.setLayout(null);
		
	}
	
	public void configuracoesLabel()
	{
		lbBackTelaInicial = new JLabel(backTelaInicial);
		lbBackTelaInicial.setBounds(0, 0, 600, 400);
		
		lbBotaoContinuar = new JLabel(botaoContinuar);
		lbBotaoContinuar.setBounds(465, 288, 130, 50);
		lbBotaoContinuar.addMouseListener(this);
		
		lbBackTelaNivel = new JLabel(backTelaNivel);
		lbBackTelaNivel.setBounds(0, 0, 600, 400);
		
		lbSnake = new JLabel(CabecaSnakeDireita);
		lbSnake.addKeyListener(this);
		
		lbComida = new JLabel(comida[1]);
		
		lbTempoJogo = new JLabel(""+num);
		lbTempoJogo.setBounds(100, 100, 100, 50);
		
		lbPontos = new JLabel("0");
		lbPontos.setBounds(10, 10, 100, 30);
		
		lbCaldaSnake = new JLabel[100];
	}
	
	public void configuracoesRadioButton()
	{
		rbFacil = new JRadioButton("");
		rbNormal = new JRadioButton("");
		rbDificil = new JRadioButton("");
		
		rbFacil.setBounds(10, 55, 20, 16);
		rbNormal.setBounds(10, 135, 20, 16);
		rbDificil.setBounds(10, 220, 20, 16);
		
		
		rbFacil.addItemListener(this);
		rbNormal.addItemListener(this);
		rbDificil.addItemListener(this);
		
		bgNiveis = new ButtonGroup();
		bgNiveis.add(rbFacil);
		bgNiveis.add(rbNormal);
		bgNiveis.add(rbDificil);
	}
	
	public void novoJogo()
	{
		tempo.stop();
		rbFacil.setSelected(false);
		rbNormal.setSelected(false);
		rbDificil.setSelected(false);
		pontos = 1;
		lbPontos.setText("0");
		lbTempoJogo.setText("0");
	}
	
	public void inicioJogo()
	{
		pnTelaPrincipal.requestFocus();
		tempo.start();
		ii = 0;
		
		posxComida = random.nextInt(80) + 60;
		posyComida = random.nextInt(80) + 60;
		
		posxCorpo[0] = posxSnake - 25;
        posyCorpo[0] = posySnake - 25;
				
		tipoComida = random.nextInt(24);
		lbComida.setIcon(comida[tipoComida]);
		
		lbComida.setBounds(posxComida, posyComida, 32, 30);
		
		posxSnake = random.nextInt(80) + 60;
		posySnake = random.nextInt(80) + 60;
		
		lbSnake.setBounds(posxSnake, posySnake, 30, 30);
		
		lbCaldaSnake[1] = new JLabel(caldaSnake);
		pnTelaPrincipal.add(lbCaldaSnake[1]);
		lbCaldaSnake[1].setBounds(posxSnake, posySnake, 26, 26);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btJogar)
		{
			String nivel = "";
			
			if(deciIndice == 1)
			{
			
			  nivel = "fácil";
			}
			else if(deciIndice == 2)
			{
				nivel = "normal";
			}
			else if(deciIndice == 3)
			{
				nivel = "difícil";
			}
			
			if(deciIndice == 1 || deciIndice == 2 || deciIndice == 3)
			{
				JOptionPane.showMessageDialog(null, "Você selecionou "+nivel);
				pnNiveis.setVisible(false);
				super.getContentPane().add(pnTelaPrincipal);
			}
			inicioJogo();
		}
		if(e.getSource() == miNovoJogo)
		{
		   	novoJogo();
		    pnNiveis.setVisible(false);
			pnTelaInicial.setVisible(true);
			pnTelaInicial.requestFocus();
		}
		
		if(e.getSource() == miSair)
		{
			System.exit(0);
		}
		
		if(e.getSource() == miComandos)
		{
			JOptionPane.showMessageDialog(null, "W: Cima\nS: Baixo\nA: Esquerda\nD: Direita", "Comandos", 1);
		}
		
		if(e.getSource() == miSobre)
		{
			ImageIcon icon = new ImageIcon(getClass().getResource("imagens/IconeSobre.png"));
			JOptionPane.showMessageDialog(null, "Desenvolvido por Brayan A. Bedritchuk", "Sobre", 1, icon);
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if(e.getItemSelectable() == rbFacil)
		{
			lbBackTelaNivel.setIcon(backIndiceFacil);
			velocidadeTimer = 300;
			deciIndice = 1;
			nivel = "Facil";
		}
		
		if(e.getItemSelectable() == rbNormal)
		{
			lbBackTelaNivel.setIcon(backIndiceNormal);
			velocidadeTimer = 200;
			deciIndice = 2;
			nivel = "Normal";
		}
		
		if(e.getItemSelectable() == rbDificil)
		{
			lbBackTelaNivel.setIcon(backIndiceDificil);
			velocidadeTimer = 100;
			deciIndice = 3;
			nivel = "Dificil";
		}
		
		CabecaSnakeBaixoDireita = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+"BaixoDireita.png"));
		CabecaSnakeBaixoEsquerda = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+"BaixoEsquerda.png"));
		CabecaSnakeCimaDireita = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+"CimaDireita.png"));
		CabecaSnakeCimaEsquerda = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+"CimaEsquerda.png"));
		CabecaSnakeDireita = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+"Direita.png"));
		CabecaSnakeEsquerda = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+"Esquerda.png"));
		caldaSnake = new ImageIcon(getClass().getResource("imagens/MovimentosSnake/Snake"+nivel+".png"));
		
		tempo = new Timer(velocidadeTimer, acao);
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		lbBotaoContinuar.setIcon(botaoContinuar3);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		lbBotaoContinuar.setIcon(botaoContinuar2);
	}
	
	public void mouseEntered(MouseEvent e)
	{
		lbBotaoContinuar.setIcon(botaoContinuar2);
	}
	
	public void mouseExited(MouseEvent e)
	{
		lbBotaoContinuar.setIcon(botaoContinuar);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		pnTelaInicial.setVisible(false);
	
		if(vezesExecutado == 0){
			super.getContentPane().add(pnNiveis);
			vezesExecutado = 1;
		}
	
		pnNiveis.setVisible(true);
	}
	
	public void keyPressed(KeyEvent e)
	{	
		if(e.getKeyCode() == e.VK_RIGHT)
		{
			if(ultimoMovimento != e.VK_LEFT && ultimoMovimento != e.VK_RIGHT)
			{
			
				ii = 1;
				lbSnake.setIcon(CabecaSnakeDireita);
			}
		
		}
		if(e.getKeyCode() == e.VK_LEFT)
		{	
			if(ultimoMovimento != e.VK_RIGHT && ultimoMovimento != e.VK_LEFT)
			{
				ii = 2;
				lbSnake.setIcon(CabecaSnakeEsquerda);
			}
		
		}
		if(e.getKeyCode() == e.VK_UP)
		{	
			ii = 4;
			
			if(ultimoMovimento == e.VK_LEFT)
				lbSnake.setIcon(CabecaSnakeCimaEsquerda);
			else
				lbSnake.setIcon(CabecaSnakeCimaDireita);
			
			
		}
		if(e.getKeyCode() == e.VK_DOWN)
		{	
			ii = 3;
			if(ultimoMovimento == e.VK_LEFT)
				lbSnake.setIcon(CabecaSnakeBaixoEsquerda);
			else
				lbSnake.setIcon(CabecaSnakeBaixoDireita);
			
		}
			ultimoMovimento = e.getKeyCode();
	}
	
	public void keyReleased(KeyEvent e)
	{
		
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public void focusGained(FocusEvent e)
	{
		if(e.getSource() == mOpcoes)
			tempo.stop();
	}
	
	public void focusLost(FocusEvent e)
	{
		
	}
		
	public static void main(String[] args)
	{
		Snake frame = new Snake();	
	}
}
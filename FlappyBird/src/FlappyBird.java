import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    private int frameWidth = 360;                    // Lebar panel permainan
    private int frameHeight = 640;                   // Tinggi panel permainan
    private Image backgroundImage;                   // Gambar latar belakang
    private Image birdImage;                         // Gambar burung
    private Image lowerPipeImage;                    // Gambar pipa bawah
    private Image upperPipeImage;                    // Gambar pipa atas

    // Atribut pemain
    private int playerStartPosX = frameWidth / 8;    // Posisi awal X pemain
    private int playerStartPosY = frameHeight / 2;   // Posisi awal Y pemain
    private int playerWidth = 34;                    // Lebar pemain
    private int playerHeight = 24;                   // Tinggi pemain
    Player player;                                  // Objek pemain

    // Atribut pipa
    private int pipeStartPosX = frameWidth;          // Posisi awal X pipa
    private int pipeStartPosY = 0;                   // Posisi awal Y pipa
    private int pipeWidth = 64;                      // Lebar pipa
    private int pipeHeight = 512;                    // Tinggi pipa
    private ArrayList<Pipe> pipes;                   // Daftar pipa

    Timer gameLoop;                                  // Timer untuk pergerakan permainan
    Timer pipesCooldown;                             // Timer untuk penempatan pipa

    boolean gameOver = false;                        // Status game over

    private double score;                            // Skor
    private JLabel scoreLabel;                       // Label untuk menampilkan skor
    private int gravity = 1;                         // Gravitasi

    // Konstruktor
    public FlappyBird(){
        setPreferredSize(new Dimension(frameWidth, frameHeight));  // Atur dimensi panel permainan
        setFocusable(true);                                         // Mengatur fokus ke panel permainan
        addKeyListener(this);                                      // Menambahkan key listener

        // Mengambil gambar latar belakang, burung, pipa atas, dan pipa bawah dari sumber daya
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bintang.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Membuat objek pemain
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();  // Membuat ArrayList untuk pipa-pipa

        // Timer untuk penempatan pipa-pipa
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pipa");
                placePipes();  // Panggil method placePipes untuk menempatkan pipa-pipa
            }
        });
        pipesCooldown.start();  // Memulai timer untuk penempatan pipa-pipa

        gameLoop = new Timer(1000/60, this);  // Timer untuk pergerakan permainan
        gameLoop.start();  // Memulai timer untuk pergerakan permainan

        score = 0;  // Inisialisasi skor
        scoreLabel = new JLabel("Skor: " + String.format("%.0f", score));  // Label untuk menampilkan skor
        scoreLabel.setForeground(Color.WHITE);  // Warna teks skor
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));  // Font untuk teks skor
        add(scoreLabel);  // Menambahkan label skor ke panel permainan
    }

    // Method untuk menggambar komponen panel permainan
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    // Method untuk menggambar objek di panel permainan
    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);  // Menggambar latar belakang

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);  // Menggambar pemain

        // Menggambar pipa-pipa
        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    // Method untuk menggerakkan objek di panel permainan
    public void move(){
        player.setVelocityY(player.getVelocityY() + gravity);  // Mengatur pergerakan vertikal pemain
        player.setPosY(player.getPosY() + player.getVelocityY());  // Mengatur posisi vertikal pemain
        player.setPosY(Math.max(player.getPosY(), 0));  // Membatasi posisi pemain agar tidak melewati batas atas layar

        // Iterasi untuk setiap pipa
        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX()+pipe.getVelocityX());  // Menggeser pipa ke kiri

            // Jika pemain melewati pipa
            if (!pipe.isPassed() && player.getPosX() > pipe.getPosX() + pipe.getWidth()){
                score = score + 0.5 ;  // Tambah skor
                pipe.setPassed(true);  // Set status pipa sudah dilewati
                scoreLabel.setText("Skor: " + String.valueOf((int) score));  // Update label skor
            }

            // Jika terjadi tabrakan antara pemain dan pipa
            if (collision(player, pipe)){
                gameOver = true;  // Set status game over
            }

        }

        // Jika pemain jatuh di luar layar
        if (player.getPosY() > frameHeight){
            gameOver = true;  // Set status game over
        }
    }

    // Method untuk menempatkan pipa-pipa di panel permainan
    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));  // Posisi Y acak untuk pipa atas
        int openingSpace = frameHeight/4;  // Jarak antara pipa atas dan pipa bawah

        // Membuat pipa atas
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        // Membuat pipa bawah
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    // Method untuk mendeteksi tabrakan antara pemain dan pipa
    boolean collision(Player player, Pipe pipe){
        return  player.getPosX() < pipe.getPosX() + pipe.getWidth() &&
                player.getPosX() + player.getWidth() > pipe.getPosX() &&
                player.getPosY() < pipe.getPosY() + pipe.getHeight() &&
                player.getPosY() + player.getHeight() > pipe.getPosY();
    }

    // Method untuk merestart permainan
    public void restartGame(){
        // Mengatur ulang posisi pemain
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        // Menghapus semua pipa
        pipes.clear();

        // Mengatur ulang status game over dan skor
        gameOver = false;
        score = 0;
        scoreLabel.setText("Skor: " + String.format("%.0f", score));

        // Memulai kembali timer untuk pergerakan permainan dan penempatan pipa
        gameLoop.start();
        pipesCooldown.start();
    }

    // Method untuk menangani peristiwa actionPerformed dari ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        move();    // Memanggil method move untuk menggerakkan objek
        repaint(); // Memanggil method repaint untuk menggambar ulang panel permainan
        // Menampilkan pesan game over jika permainan berakhir
        if (gameOver){
            pipesCooldown.stop();  // Menghentikan timer penempatan pipa
            gameLoop.stop();       // Menghentikan timer pergerakan permainan
            JOptionPane.showMessageDialog(this, "Game Over! Tekan 'R' atau 'Spasi' untuk restart.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method untuk menangani ketika tombol keyboard ditekan
    @Override
    public void keyPressed(KeyEvent e) {
        // Jika tombol spasi ditekan
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);  // Mengatur pergerakan vertikal pemain ke atas

            // Jika game over dan tombol spasi ditekan, restart permainan
            if (gameOver){
                restartGame();
            }
        }
        // Jika tombol R ditekan
        else if (e.getKeyCode() == KeyEvent.VK_R) {
            // Jika game over dan tombol R ditekan, restart permainan
            if (gameOver) {
                restartGame();
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {}
}

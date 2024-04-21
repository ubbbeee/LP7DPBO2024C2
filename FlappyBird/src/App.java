import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class App {
    public static void main(String[] args) {
        // Buat Frame untuk GUI Form baru
        JFrame startFrame = new JFrame("Burung Kang Bintang"); // Membuat frame dengan judul "Burung Kang Bintang"
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Menetapkan operasi default saat tombol close ditekan
        startFrame.setSize(280, 480); // Menetapkan ukuran frame
        startFrame.setLocationRelativeTo(null); // Menetapkan lokasi frame ke tengah layar
        startFrame.setResizable(false); // Mencegah pengguna mengubah ukuran frame

        // Tambahkan JPanel dengan background flappy bird
        JPanel panel = new JPanel() { // Membuat panel
            @Override
            protected void paintComponent(Graphics g) { // Menggambar latar belakang
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(App.class.getResource("assets/background.png")); // Mengambil gambar latar belakang dari sumber daya
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null); // Menggambar gambar latar belakang
            }
        };
        panel.setLayout(new BorderLayout()); // Menetapkan layout panel menjadi BorderLayout

        // Tambahkan label "Game Flappy Bird" di tengah atas
        JLabel titleLabel = new JLabel("Burung Kang Bintang", SwingConstants.CENTER); // Membuat label dengan teks tengah
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Menetapkan font label
        titleLabel.setBorder(new EmptyBorder(25, 0, 0, 0)); // Menetapkan padding
        titleLabel.setForeground(Color.WHITE); // Menetapkan warna teks
        panel.add(titleLabel, BorderLayout.NORTH); // Menambahkan label ke panel di bagian utara

        // Tambahkan gambar burung di bawah label "Game Flappy Bird"
        ImageIcon birdIcon = new ImageIcon(App.class.getResource("assets/bintang.png")); // Mengambil gambar burung dari sumber daya
        Image birdImage = birdIcon.getImage().getScaledInstance(34, 24, Image.SCALE_SMOOTH); // Mengubah ukuran gambar burung
        ImageIcon resizedBirdIcon = new ImageIcon(birdImage); // Membuat objek ImageIcon dari gambar yang diubah ukurannya
        JLabel birdLabel = new JLabel(resizedBirdIcon); // Membuat label untuk gambar burung
        panel.add(birdLabel, BorderLayout.CENTER); // Menambahkan label ke panel di tengah

        // Buat objek JButton untuk tombol "Mulai"
        JButton startButton = new JButton("Mulai"); // Membuat tombol dengan teks "Mulai"
        startButton.addActionListener(e -> { // Menambahkan aksi ketika tombol ditekan
            // Tutup GUI Form
            startFrame.dispose(); // Menutup frame

            // Buka JFrame game FlappyBird
            JFrame gameFrame = new JFrame("Burung Kang Bintang"); // Membuat frame game dengan judul "Burung Kang Bintang"
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Menetapkan operasi default saat tombol close ditekan
            gameFrame.setSize(360, 640); // Menetapkan ukuran frame game
            gameFrame.setLocationRelativeTo(null); // Menetapkan lokasi frame ke tengah layar
            gameFrame.setResizable(false); // Mencegah pengguna mengubah ukuran frame

            // Buat objek JPanel
            FlappyBird flappyBird = new FlappyBird(); // Membuat objek game FlappyBird
            gameFrame.add(flappyBird); // Menambahkan objek game ke frame
            gameFrame.pack(); // Mengatur ukuran frame agar sesuai dengan kontennya
            flappyBird.requestFocus(); // Meminta fokus untuk game
            gameFrame.setVisible(true); // Menampilkan frame game
        });

        // Buat objek JButton untuk tombol "Keluar"
        JButton exitButton = new JButton("Keluar"); // Membuat tombol dengan teks "Keluar"
        exitButton.addActionListener(e -> { // Menambahkan aksi ketika tombol ditekan
            // Tutup GUI Form
            startFrame.dispose(); // Menutup frame
        });

        // Tambahkan tombol "Mulai" dan "Keluar" ke dalam JPanel di tengah bawah
        JPanel buttonPanel = new JPanel(new GridBagLayout()); // Membuat panel untuk tombol dengan layout GridBagLayout
        buttonPanel.setOpaque(false); // Menjadikan panel transparan
        GridBagConstraints gbc = new GridBagConstraints(); // Membuat objek GridBagConstraints untuk mengatur tata letak
        gbc.insets = new Insets(0, 0, 50, 0); // Menetapkan jarak antara tombol dan bawah panel
        buttonPanel.add(startButton, gbc); // Menambahkan tombol "Mulai" ke panel dengan GridBagConstraints
        gbc.gridy = 1; // Pindah ke baris berikutnya
        buttonPanel.add(exitButton, gbc); // Menambahkan tombol "Keluar" ke panel dengan GridBagConstraints

        panel.add(buttonPanel, BorderLayout.SOUTH); // Menambahkan panel tombol ke panel utama di bagian selatan

        // Tambahkan JPanel ke dalam JFrame
        startFrame.add(panel); // Menambahkan panel utama ke frame
        startFrame.setVisible(true); // Menampilkan frame
    }
}

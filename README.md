# LP7DPBO2024C2

Saya Alfen Fajri Nurulhaq dengan NIM 2201431 mengerjakan soal LP 7 dalam Praktikum mata kuliah Desain dan Pemrograman Berbasis Objek, untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamin.

## Desain Program
App untuk membuat tampilan awal permainan Flappy Bird.
Menggunakan Java Swing untuk membuat GUI.
Memuat elemen-elemen GUI seperti judul, gambar latar belakang, tombol mulai, dan tombol keluar.
Ketika tombol "Mulai" ditekan, game Flappy Bird akan dimulai dengan membuka jendela baru.
Ketika tombol "Keluar" ditekan, aplikasi akan ditutup.

FlappyBird
Kelas ini mewakili permainan Flappy Bird itu sendiri.
Mewarisi kelas JPanel untuk menampilkan permainan dalam sebuah panel.
Mengatur logika permainan seperti gerakan pemain, pergerakan pipa, dan penanganan tabrakan.
Menyediakan timer untuk mengatur pergerakan dan penempatan pipa secara periodik.
Menggunakan KeyListener untuk merespons input dari pengguna, seperti tombol spasi untuk membuat burung melompat.
Menampilkan skor permainan dan mengatur logika game over.

Player
Kelas ini merepresentasikan pemain (burung) dalam permainan.
Menyimpan atribut seperti posisi, lebar, tinggi, gambar, dan kecepatan vertikal pemain.
Berfungsi untuk mengatur dan mendapatkan informasi tentang pemain.

Pipe
Kelas ini merepresentasikan pipa dalam permainan.
Menyimpan atribut seperti posisi, lebar, tinggi, gambar, dan kecepatan horizontal pipa.
Berfungsi untuk mengatur dan mendapatkan informasi tentang pipa.

## Alur Program
Saat program dijalankan, sebuah GUI form utama ditampilkan
Di GUI form ini, pengguna memiliki opsi untuk memulai permainan dengan menekan tombol "Mulai" atau keluar dari aplikasi dengan menekan tombol "Keluar".
Saat permainan dimulai, game baru dibuka
Pemain (burung) akan ditampilkan di tengah layar, sedangkan pipa akan muncul dari sisi kanan layar dan bergerak ke kiri.
Pemain dapat menggerakkan burung dengan menekan tombol spasi, yang akan membuat burung melompat.
Tujuan permainan adalah untuk menjaga burung agar tidak menabrak pipa dan mencetak skor sebanyak mungkin dengan melewati pipa.
Permainan akan berlanjut hingga burung menabrak pipa atau jatuh ke bawah layar lalu game over.
Saat permainan berakhir, pesan "Game Over" akan ditampilkan, dan pengguna dapat menekan tombol "R" atau "Spasi" untuk memulai kembali permainan.

## Screen Record
https://github.com/ubbbeee/LP7DPBO2024C2/assets/120569318/04b9842c-e8ec-4e30-9882-03e41ee39380




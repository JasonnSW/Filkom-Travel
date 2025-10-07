public abstract class Tanaman {
    private int masaHidup;
    private int lamaHidup = 0;
    private int berbuah;
    private int buah = 0;
    private double perkembangan;
    private double prosesBerbuah;

    public int getMasaHidup() {
        return masaHidup;
    }

    public int getLamaHidup() {
        return lamaHidup;
    }

    public int getBerbuah() {
        return berbuah;
    }

    public int getBuah() {
        return buah;
    }

    public double getPerkembangan() {
        return perkembangan;
    }

    public double getProsesBerbuah() {
        return prosesBerbuah;
    }

    public void setMasaHidup(int masaHidup) {
        this.masaHidup = masaHidup;
    }

    public void setLamaHidup(int lamaHidup) {
        this.lamaHidup = lamaHidup;
    }

    public void setBerbuah(int berbuah) {
        this.berbuah = berbuah;
    }

    public void setBuah(int buah) {
        this.buah = buah;
    }

    public void setPerkembangan(double perkembangan) {
        this.perkembangan = perkembangan;
    }

    public void setProsesBerbuah(double prosesBerbuah) {
        this.prosesBerbuah = prosesBerbuah;
    }

    public abstract void berkembang();

    public String status() {
        if (getLamaHidup() >= getMasaHidup()) {
            return "Mati";
        }
        return "Hidup";
    }

    @Override
    public String toString() {
        String masaHidupInfo = "Masa Hidup\t: " + getMasaHidup() + " hari";
        String lamaHidupInfo = "Umur Tanaman\t: " + getLamaHidup() + " hari";
        String buah = "Menghasilkan\t: " + getBuah();
        String statusInfo = "Status\t\t: " + status();

        return masaHidupInfo + "\n" + lamaHidupInfo + "\n" + buah + "\n" + statusInfo + "\n";
    }

}
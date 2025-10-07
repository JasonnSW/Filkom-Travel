public class Tomat extends Tanaman implements Perawatan {

    @Override
    public void berkembang() {
        super.setLamaHidup(super.getLamaHidup() + 1);
        super.setProsesBerbuah(super.getProsesBerbuah() + (super.getBerbuah() * super.getPerkembangan()));
        double prosesBerbuah = super.getProsesBerbuah();

        if (prosesBerbuah >= super.getBerbuah()) {
            super.setBuah(super.getBuah() + 1);
            super.setProsesBerbuah(prosesBerbuah - super.getBerbuah());
        }
    }

    public Tomat() {
        super.setMasaHidup(100);
        super.setBerbuah(100);
        super.setPerkembangan(0.25);
        super.setProsesBerbuah(super.getProsesBerbuah() + 0.25 * super.getBerbuah());
    }

    @Override
    public void treatment() {
        super.setPerkembangan(super.getPerkembangan() + (5 / 100.0));
    }

    @Override
    public String toString() {
        String masaHidupInfo = "Masa Hidup\t: " + getMasaHidup() + " hari";
        String lamaHidupInfo = "Umur Tanaman\t: " + getLamaHidup() + " hari";
        String buah = "Menghasilkan\t: " + getBuah() + " buah Tomat";
        String statusInfo = "Status\t\t: " + status();

        return masaHidupInfo + "\n" + lamaHidupInfo + "\n" + buah + "\n" + statusInfo + "\n";
    }

}
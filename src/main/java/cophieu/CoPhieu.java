package cophieu;

public class CoPhieu {
	
	private String tenCp;
	private double gia_tham_chieu;
	private double bien_do_dd;
	private double gia_chot;
	private double phan_tram;
	private double KL_gd;
	private int ngay;
	private int thang;
	public CoPhieu() {
		
	}

	public CoPhieu(String tenCp, double gia_tham_chieu, double bien_do_dd, double gia_chot, double phan_tram,
			double kL_gd,int ngay,int thang) {
		super();
		this.tenCp = tenCp;
		this.gia_tham_chieu = gia_tham_chieu;
		this.bien_do_dd = bien_do_dd;
		this.gia_chot = gia_chot;
		this.phan_tram = phan_tram;
		KL_gd = kL_gd;
		this.ngay=ngay;
		this.thang=thang;
	}

	public String getTenCp() {
		return tenCp;
	}

	public void setTenCp(String tenCp) {
		this.tenCp = tenCp;
	}

	public double getGia_tham_chieu() {
		return gia_tham_chieu;
	}

	public void setGia_tham_chieu(double gia_tham_chieu) {
		this.gia_tham_chieu = gia_tham_chieu;
	}

	public double getBien_do_dd() {
		return bien_do_dd;
	}

	public void setBien_do_dd(double bien_do_dd) {
		this.bien_do_dd = bien_do_dd;
	}

	public double getGia_chot() {
		return gia_chot;
	}

	public void setGia_chot(double gia_chot) {
		this.gia_chot = gia_chot;
	}

	public double getPhan_tram() {
		return phan_tram;
	}

	public void setPhan_tram(double phan_tram) {
		this.phan_tram = phan_tram;
	}

	public double getKL_gd() {
		return KL_gd;
	}

	public void setKL_gd(double kL_gd) {
		KL_gd = kL_gd;
	}

	public int getNgay() {
		return ngay;
	}

	public void setNgay(int ngay) {
		this.ngay = ngay;
	}

	public int getThang() {
		return thang;
	}

	public void setThang(int thang) {
		this.thang = thang;
	}

	@Override
	public String toString()
	{
		return this.tenCp;
	}
	
	
}

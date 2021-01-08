package readinput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cophieu.CoPhieu;


public class ReadInput {

	// kiem tra so 
	static int count = 0;
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\,\\d+)?");  //match a number with optional '-' and decimal.
	}

	//chuyen ky tu thanh so thuc 
	public static double parseDouble(String str) {
		return (Double.parseDouble(str.replace(',', '.') ) );
	}

	// doc file input va luu vao 1 danh sach
	public static void readInput(String filename, List<CoPhieu> list) throws IOException {
		File a = new File(filename);
		FileReader f= new FileReader(a);
		BufferedReader br = new BufferedReader(f);
		String b;
		while((b=br.readLine())!= null){
			CoPhieu cp = new CoPhieu();
			String[] w = b.split(" ");

			for (int i=0; i< w.length; i++ ) {
				if (w[i].equals("tăng") || w[i].equals("cộng") || w[i].equals("tích") || 
						w[i].equals("nhích") || w[i].equals("lấy")) {
					if (w[i+1].indexOf('%') == w[i+1].length() -1 ) {
						cp.setPhan_tram(Math.round((parseDouble(w[i+1].substring(0,w[i+1].length()-1)))*100)/100 );
					}else {
						int tmp = i;
						do {
							tmp++;

						}while (!isNumeric(w[tmp]));
						cp.setBien_do_dd((double)Math.round(100*parseDouble(w[tmp]))/100);
					}
				}

				if (w[i].equals("trừ") || w[i].equals("giảm") || w[i].equals("mất")) {
					if (w[i+1].indexOf('%') == w[i+1].length() -1 ) {
						cp.setPhan_tram((double) Math.round(100*parseDouble(w[i+1].substring(0,w[i+1].length()-1)))/100);
					}else {
						int tmp = i;
						do {
							tmp++;

						}while (!isNumeric(w[tmp]));
						cp.setBien_do_dd((double)Math.round(-1 * parseDouble(w[tmp])*100)/100);
					}
				}

				if (w[i].equals("lên") || w[i].equals("đạt") || w[i].equals("đóng") || 
						w[i].equals("xuống") || w[i].equals("tại") || w[i].equals("còn") || 
						w[i].equals("sát")) {
					int tmp = i;
					do {
						tmp++;

					}while (!isNumeric(w[tmp]));
					cp.setGia_chot((double)Math.round(100*parseDouble(w[tmp]))/100);
				}

				if (w[i] .equals("chuyển") || w[i].equals("sang") ) {
					int tmp = i;
					do {
						tmp++;

					}while (!isNumeric(w[tmp]));
					cp.setKL_gd((double)Math.round(100*parseDouble(w[tmp]))/100);
				}

				if (w[i].equals("đầu") || w[i].equals("mốc")) {
					int tmp = i;
					do {
						tmp++;

					}while (!isNumeric(w[tmp]));
					cp.setGia_chot((double)Math.round(100*parseDouble(w[tmp]))/100);

				}

			}	

			// xu ly cac so lieu = 0 
			if (cp.getGia_chot() == 0.0 && count > 0 ) {
				cp.setGia_chot((double)Math.round(100*list.get(count-1).getGia_chot())/100);
			}

			if (cp.getGia_tham_chieu() == 0.0 ) {
				if (cp.getPhan_tram() != 0.0) {
					cp.setGia_tham_chieu((double)Math.round(100*(cp.getGia_chot() / (cp.getPhan_tram()/100 +100)))/100);
				}
				else {
					cp.setGia_tham_chieu((double)Math.round(100*(cp.getGia_chot() - cp.getBien_do_dd()))/100);
				}

			}

			if (cp.getBien_do_dd() == 0.0) {
				if ( cp.getGia_tham_chieu() > cp.getGia_chot() ) {
					cp.setBien_do_dd((double)Math.round(100*(cp.getGia_tham_chieu() - cp.getGia_chot()))/100);
				}
				else {
					cp.setBien_do_dd((double)Math.round(100*(cp.getGia_chot() - cp.getGia_tham_chieu()))/100);
				}
			}



			if (cp.getKL_gd() == 0.0) {
				Random rd = new Random();
				cp.setKL_gd((double)Math.round(100*(list.get(count -1 ).getKL_gd() + rd.nextInt(100))/2)/100);

			}

			if (cp.getPhan_tram() == 0.0)
				cp.setPhan_tram( (double)Math.round(cp.getGia_chot()/cp.getGia_tham_chieu()*100)/100);
			list.add(cp);
			count ++;

		}
		br.close();


	}
	public static void setNgay_Thang(List<CoPhieu> list,int ngay,int thang) {
		for(CoPhieu cp :list) {
			cp.setTenCp("VN-Index");
			if(thang==2) {
				if(ngay>29) {
					thang++;
					ngay=1;
				}
			}
			if(thang%2==1) {
				if(ngay>31) {
					thang++;
					ngay=1;
				}
			}
			if(thang%2==0&&thang!=2) {
				if(ngay>30) {
					thang++;
					ngay=1;
				}
			}
			cp.setNgay(ngay);
			cp.setThang(thang);
			ngay++;
		}
	}

}
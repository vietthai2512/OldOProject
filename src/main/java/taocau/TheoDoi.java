package taocau;

import java.util.List;

import cophieu.CoPhieu;

public class TheoDoi {
	//ktra xem 1 ngày trong tháng bất kì có hợp lệ hay không
	public static boolean kTraNgay(int ngay, int thang) {
		if(ngay>31||ngay<0) return false;
		else if(thang>12||thang<0) return false;
		else if(thang==2 && ngay>29) return false;
		if(thang%2==0 && ngay==31) return false;
		else return true;
	}
	//ktra ngày, tháng mà người dùng nhập vào có hợp lệ hay không
	public static boolean kTraNhap(int ngay_batdau,int thang_batdau,
			int ngay_ketthuc,int thang_ketthuc) {
		if(!kTraNgay(ngay_batdau, thang_batdau)) return false;
		else if(!kTraNgay(ngay_ketthuc, thang_ketthuc)) return false;
		else if(thang_ketthuc<thang_batdau) return false;
		else if(thang_batdau==thang_ketthuc && ngay_batdau>ngay_ketthuc) return false;
		else return true;
	}

	// ktra ngày, tháng của cp xem có thỏa mãn điều kiện ngày, tháng mà người dùng nhập không.
	public static boolean kTraDieuKien(CoPhieu cp,int ngay_batdau,int thang_batdau,
			int ngay_ketthuc,int thang_ketthuc) {
		if(cp.getThang()>thang_batdau&&cp.getThang()<thang_ketthuc) return true;
		else if(cp.getThang()==thang_batdau&&cp.getNgay()>=ngay_batdau) return true;
		else if(cp.getThang()==thang_ketthuc&&cp.getNgay()<=ngay_ketthuc) return true;
		else return false;
	}

	// tính biên độ, kl giao dịch, giá chót trung bình trong khoảng thời gian mà người dùng nhập.
	public static String theoDoi(List<CoPhieu> list,int ngay_batdau,int thang_batdau,
			int ngay_ketthuc,int thang_ketthuc)
	{
		StringBuilder tao_cau = new StringBuilder();
		
		double bien_do=0,kl_gd=0,gia_chot=0;
		int count =0;
		for(CoPhieu cp:list) {
			if(kTraDieuKien(cp, ngay_batdau, thang_batdau, ngay_ketthuc, thang_ketthuc)) {
				count++;
				bien_do=bien_do+cp.getBien_do_dd();
				kl_gd=kl_gd+cp.getKL_gd();
				gia_chot=gia_chot+cp.getGia_chot();
			}

		}
		if(count==0) 
		{
			tao_cau.append("Chưa có dữ liệu trong khoảng thời gian này");
		}
		else 
		{

			double bien_doTB= Math.round(10*(bien_do/count)/10); // làm tròn 1 số sau dấu phảy
			double kl_gdTB=Math.round(10*(kl_gd/count)/10);
			double gia_chotTB=Math.round(10*(gia_chot/count)/10);
			
			tao_cau.append("Trong khoảng thời gian từ " + ngay_batdau + "/" + thang_batdau + " đến ").
					append(ngay_ketthuc + "/" + thang_ketthuc + ", ");
			
			if(bien_do > 0) 
			{
				tao_cau.append(list.get(0).getTenCp()).append(" tăng ").
						append(bien_doTB).append(" điểm, với giá chót trung bình là ").append(gia_chotTB).
						append(", khối lượng giao dịch trung bình là ").append(kl_gdTB);
			}
			else if(bien_do < 0) 
			{
				tao_cau.append(list.get(0).getTenCp()).append(" giảm ").
				append(Math.abs(bien_doTB)).append(" điểm, với giá chót trung bình là ").append(gia_chotTB).
				append(", khối lượng giao dịch trung bình là ").append(kl_gdTB);
			}
			else if (bien_do == 0) 
			{
				tao_cau.append(list.get(0).getTenCp()).append(" có biên độ giao động không đổi ").
				append(" , với giá chót trung bình là ").append(gia_chotTB).
				append(", khối lượng giao dịch trung bình là ").append(kl_gdTB);
			}
			tao_cau.append(System.getProperty("line.separator"));
		}
		
		return tao_cau.toString();
	}
}
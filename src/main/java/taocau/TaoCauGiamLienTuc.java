package taocau;

import java.util.List;

import cophieu.CoPhieu;

public class TaoCauGiamLienTuc extends TaoCauGiam 
{
	private int soNgayLienTuc;
	private List<CoPhieu> list;
	boolean lientuc, khongco = true;
	
	// Constructor
	public TaoCauGiamLienTuc(List<CoPhieu> list, int soNgayLienTuc)
	{
		this.list = list;
		if (soNgayLienTuc < 0 || soNgayLienTuc > list.size())
			throw new IllegalArgumentException ("Không tìm được số ngày liên tục theo yêu cầu.");
		this.soNgayLienTuc = soNgayLienTuc;
	}
	
	@Override
	public String taoCauGiam(List<CoPhieu> list) 
	{
		StringBuilder tao_cau = new StringBuilder();
		
		for (int i = 0; i <= list.size() - soNgayLienTuc; i++)
		{
			double bien_do = 0, kl_gd = 0, gia_chot = 0;
			lientuc = true;
			int j;
			
			for (j = i; j < i + soNgayLienTuc - 1; j++)
			{
				if (list.get(j).getBien_do_dd() > 0)
				{
					lientuc = false;
					break;
				}
				else
				{
					bien_do = bien_do + list.get(j).getBien_do_dd();
					kl_gd = kl_gd + list.get(j).getKL_gd();
					gia_chot = gia_chot + list.get(j).getGia_chot();
				}
			}
			if (lientuc) 
			{
				khongco = false;
				double bien_doTB = Math.round(10 * (bien_do/soNgayLienTuc)/10); // làm tròn 1 số sau dấu phảy
				double kl_gdTB = Math.round(10 * (kl_gd/soNgayLienTuc)/10);
				double gia_chotTB = Math.round(10 * (gia_chot/soNgayLienTuc)/10);
				
				tao_cau.append("Từ ").append(list.get(i).getNgay() + "/" + list.get(i).getThang()).append(" đến ").
									  append(list.get(j).getNgay() + "/" + list.get(j).getThang()).append(" , ").
									  append(list.get(i).getTenCp() + " giảm liên tục. ");

				if (bien_do > 0) 
				{
				tao_cau.append("Trong khoảng thời gian này, ").append(list.get(0).getTenCp()).append(" tăng ").
						append(bien_doTB).append(" điểm, với giá chót trung bình là ").append(gia_chotTB).
						append(", khối lượng giao dịch trung bình là ").append(kl_gdTB);
				}
				else if (bien_do < 0) 
				{
					tao_cau.append("Trong khoảng thời gian này, ").append(list.get(0).getTenCp()).append(" giảm ").
							append(Math.abs(bien_doTB)).append(" điểm, với giá chót trung bình là ").append(gia_chotTB).
							append(", khối lượng giao dịch trung bình là ").append(kl_gdTB);
				}
				else if (bien_do == 0) 
				{
					tao_cau.append("Trong khoảng thời gian này, ").append(list.get(0).getTenCp()).append(" có biên độ giao động không đổi ").
							append(" , với giá chót trung bình là ").append(gia_chotTB).
							append(", khối lượng giao dịch trung bình là ").append(kl_gdTB);
				}
				tao_cau.append(System.getProperty("line.separator"));
				
			}
		}
		if (khongco)
			tao_cau.append("Không có cổ phiếu nào giảm liên tục trong " + soNgayLienTuc + " ngày.");
		
		return tao_cau.toString();
	}
}

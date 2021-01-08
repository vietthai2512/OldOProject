package taocau;

import java.util.List;

import cophieu.CoPhieu;

public class TaoCauTangNhe extends TaoCauTang
{
	public String taoCauTang(List<CoPhieu> list) 
	{
		String[] tang_nhe = {"Kết phiên giao dịch ngày "," tăng nhẹ "," điểm từ "," lên "};
		StringBuilder tao_cau = new StringBuilder();
		
		for (CoPhieu cp : list)
		{
			if (cp.getBien_do_dd() < 2 && cp.getBien_do_dd() > 0)
			{
				tao_cau.append(tang_nhe[0]).append(cp.getNgay()).append("/").
							append(cp.getThang() + " ").append(cp.getTenCp()).append(tang_nhe[1]).
							append(cp.getBien_do_dd()).append(tang_nhe[2]).append(cp.getGia_tham_chieu()).
							append(tang_nhe[3]).append(cp.getGia_chot()).append(" điểm.").
							append(System.getProperty("line.separator"));
			}
		}
		return tao_cau.toString();
	}

}

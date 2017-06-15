package Unlimited.min.point.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Unlimited.min.point.action.PointAction;
import Unlimited.min.point.command.PointActionCommand;
import Unlimited.min.point.dao.PointDAO;
import hs.member.DAO.MemberDAO;
import hs.member.model.MemberDTO;

public class PointExchangeService implements PointAction {

	@Override
	public PointActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ad");
		String buyer = request.getParameter("buyer");
		String seller = request.getParameter("seller");
		
		int dealing_point = Integer.parseInt(request.getParameter("dealing_point"));
		PointDAO pointDAO = new PointDAO();
		MemberDAO memberDAO = new MemberDAO();

		MemberDTO sellerDTO = memberDAO.memberDetailInfo2(seller);
//		System.out.println(sellerDTO);
		MemberDTO buyerDTO = memberDAO.memberDetailInfo2(buyer);
//		System.out.println(buyerDTO);
		pointDAO.insertSellHistory(dealing_point, buyerDTO, sellerDTO);
		pointDAO.insertBuyHistory(-(double)dealing_point, buyerDTO, sellerDTO);
		pointDAO.chargeMbPt(dealing_point, sellerDTO.getId(), sellerDTO.getPoint());
		pointDAO.chargeMbPt(-(double)dealing_point, buyerDTO.getId(), buyerDTO.getPoint());
				
		return null;
	}
	
	
	
	
}

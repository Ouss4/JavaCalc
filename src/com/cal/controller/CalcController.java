package com.cal.controller;

import com.cal.model.AbstractModel;

public class CalcController implements ControllerInterface{
	
	private AbstractModel model;
	private String number;
	
	public CalcController(AbstractModel model){
		this.model = model;
		number = "";
	}
	
	public void numberClicked(String num){
		number += num;
		this.model.notifyNumber(number);
	}
	
	public void operatorClicked(String op){
		if(op == "="){
			this.model.setSecondNumber(Double.valueOf(number));
			this.model.getResult();
		}
		else{
			this.model.setFirstNumber(Double.valueOf(number));
			this.model.setOperator(op);
			number = "0";
			this.model.notifyNumber(number);
		}
		number = "";
	}

	@Override
	public void reset() {
		number = "";
		this.model.reset();
	}
}

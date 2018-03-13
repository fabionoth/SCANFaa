package br.com.AngloAmericano.SCANFaa.control;

public class Valida {
	public boolean validaString(String valor) {
		if(valor.isEmpty()){
			return false;
		}else {
			valor = valor.trim();
			valor = valor.replace(" ", "");
			boolean v = true;
			char[] c = valor.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (Character.isLetter(c[i])) {
					v = false;
					break;
				} 
				else if(c[i] == '.'){
					v = false;
					break;
				}
				else if(c[i] == ','){
					v = false;
					break;
				}
				else if(c[i] == ' '){
					v = false;
					break;
				}
			}
			return v;
		}
	}
	public boolean validaS(String valor) {
		if(valor.isEmpty()){
			return false;
		}else {
			valor = valor.trim();
			valor = valor.replace(" ", "");
			valor = valor.replace(",", ".");
			boolean v = true;
			char[] c = valor.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (Character.isLetter(c[i])) {
					v = false;
					break;
				} 
				else if(c[i] == ' '){
					v = false;
					break;
				}
				else if(c[i] == ','){
					v = false;
					break;
				}
			}
			return v;
		}
	}
	
	

}

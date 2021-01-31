package br.com.project.util.all;

public enum EstatusPersistencia {
	
	ERRO("Erro"), SUCESSO("Sucesso"), 
	OBJETO_REFERENCIADO("Esse Objeto n�o pode ser Apagado por possuir Refer�ncias ao mesmo!");
	
	private String name;
	
	private EstatusPersistencia(String s) {
		this.name = s;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}

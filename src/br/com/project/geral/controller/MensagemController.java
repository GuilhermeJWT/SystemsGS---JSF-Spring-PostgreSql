package br.com.project.geral.controller;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Mensagem;

@Controller
public class MensagemController extends ImplementacaoCrud<Mensagem> implements InterfaceCrud<Mensagem>{

	private static final long serialVersionUID = 1L;
	
	@Override
	public Mensagem merge(Mensagem obj) throws Exception {
		return super.merge(obj);
	}

}

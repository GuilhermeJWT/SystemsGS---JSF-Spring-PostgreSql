package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;

import br.com.framework.hibernate.session.HibernateUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapperd;

	final FacesContext facesContext = FacesContext.getCurrentInstance();

	final Map<String, Object> RequestMap = facesContext.getExternalContext().getRequestMap();

	final NavigationHandler navigateHandler = facesContext.getApplication().getNavigationHandler();

	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapperd = exceptionHandler;
	}

	/* Sobrescreve o metodo ExceptionHandler que retorna a pilha de exceções */
	@Override
	public ExceptionHandler getWrapped() {
		return wrapperd;
	}

	/*
	 * Sobrescreve o metodo gandler que é responsavel por manipular as exceções do
	 * JSF
	 */
	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();

		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			/* Recupera a exeção do contexto */
			Throwable exception = context.getException();
			/* Aqui trabalhamos a exeção */
			try {

				RequestMap.put("exceptionMessage", exception.getMessage());

				if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("ConstraintViolationException") != -1) {

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
							.SEVERITY_WARN, "Registro não pode ser Removido por estar associado!", ""));
					
				}else if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
							.SEVERITY_ERROR, "Registro foi atualizado ou excluido por outro Usuário, Consulte Novamente!", ""));
					
				}else {
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
							.SEVERITY_FATAL, "O sistema se recuperou de um erro Inesperado!", ""));
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
							.SEVERITY_INFO , "Você pode continuar usando o sistema Normalmente!", ""));
					
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage
							.SEVERITY_FATAL , "O erro foi causado por:\n" + exception.getMessage(), ""));
					
					/*PrimeFaces*/
					RequestContext.getCurrentInstance().execute("alert('O sistema se recuperou de um erro Inesperado!')");
					
					RequestContext.getCurrentInstance().
					showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro", "O sistema se recuperou de um erro Inesperado!"));
					
					/*Redireciona para a pagina de erro*/
					navigateHandler.handleNavigation(facesContext, null,
							"/error/error.jsf?faces-redirect=true&expired=true");
				}
				
				facesContext.renderResponse();

			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				/* Imprime o erro no Console */
				exception.printStackTrace();
				iterator.remove();
			}
		}

		getWrapped().handle();

	}

}

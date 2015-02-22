package br.com.yaw.spgae.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.yaw.spgae.dao.ClientDAO;
import br.com.yaw.spgae.model.Client;

/**
 * Principal componente do framework <code>Spring MVC</code>, esse é o controller do cadastro de mercadorias. 
 * 
 * <p>Tem como responsabilidade: definir o mapeamento de navegação, acionar validadores e conversores de dados, 
 * fornecer e receber os dados da camada de visão (<code>JSP</code>).</p>
 * 
 * <p>Os métodos de navegação, retornam a url definida no Tiles. Veja também o arquivo <code>views.xml</code>.</p>
 * 
 * @author YaW Tecnologia
 */
@RequestMapping(value="/client")
@Controller
public class ClientController {

	private static Logger log = Logger.getLogger(ClientController.class);
	
	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para resolver a instancia do <code>DAO</code>.
	 */
	@Autowired
	private ClientDAO dao;
	
	@Autowired @Qualifier("sobreApp") 
	private ArrayList<?> sobre;

	/**
	 * Ponto de entrada da aplicação ("/").
	 * @param uiModel recebe a lista de mercadorias.
	 * @return url para a pagina de listagem de mercadorias.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String listar(Model uiModel) {
		uiModel.addAttribute("clients", getDataSource().getAll());
		return "clientlista";
	}
	
	/**
	 * Método executado antes de carregar a tela de inclusão de mercadoria.
	 * @param uiModel
	 * @return url da página de inclusão.
	 */
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String criarForm(Model uiModel) {
		uiModel.addAttribute("client", new Client());
		uiModel.addAttribute("active", "clientincluir");
		log.debug("Pronto para incluir Client");
		return "clientincluir";
	}
	
	/**
	 * Método executado na inserção da mercadoria.
	 * @param mercadoria instância com os dados preenchidos na tela
	 * @param bindingResult componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado volta para a pagina de inclusão.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String criar(@Valid Client client, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
            uiModel.addAttribute("client", client);
            uiModel.addAttribute("active", "clientincluir");
            log.debug("erro ao incluir client. ");
            return "clientincluir";
        }
		dao.save(client);
		getDataSource().add(client);
		log.debug("client persistida: "+client.getId());
		return "redirect:/client";
	}
	
	/**
	 * Método executado antes de carregar a tela de edição de mercadorias.
	 * @param id da mercadoria que deve ser editada.
	 * @param uiModel armazena o objeto mercadoria que deverá ser alterado.
	 * @return url da página de edição.
	 */
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String editarForm(@PathVariable("id") Long id, Model uiModel) {
		Client m = dao.findById(id);
		if (m != null) {
			uiModel.addAttribute("client", m);
			log.debug("Pronto para editar client");
		}
		return "clienteditar";
	}
	
	/**
	 * Método executado ao salvar a edição de mercadoria.
	 * @param mercadoria objeto com os dados enviados pela tela.
	 * @param bindingResult componente usado para verificar problemas com validação.
	 * @param uiModel
	 * @return a url para a listagem, se algum erro de validação for encontrado volta para a pagina de edição.
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public String editar(@Valid Client client, BindingResult bindingResult, Model uiModel) {
		if (bindingResult.hasErrors()) {
            uiModel.addAttribute("client", client);
            return "clienteditar";
        }
		dao.save(client);
		getDataSource().update(client);
		log.debug("Client atualizada: "+client.getId());
		return "redirect:/client";
	}
	
	/**
	 * Método executado na exclusão da mercadoria.
	 * @param id da mercadoria que deverá ser removida.
	 * @param uiModel
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String remover(@PathVariable("id") Long id, Model uiModel) {
		Client m = dao.findById(id);
		if (m != null) {
			dao.remove(m);
			getDataSource().remove(m);
			log.debug("Client removido: "+m.getId());
		}
		return "redirect:/client";
    }
	
	/**
	 * Método executado para sincronizar os dados do <code>DataSource</code>. Botão atualizar.
	 * @return url da página de listagem.
	 */
	@RequestMapping(value = "synch", method = RequestMethod.GET)
	public String atualizar() {
		getDataSource().synch(dao.getAll());
		return "redirect:/";
	}
	
	@RequestMapping(value = "sobre", method = RequestMethod.GET)
	public String sobre(Model uiModel) {
		uiModel.addAttribute("sobre",sobre);
		uiModel.addAttribute("active", "sobre");
		return "sobre";
	}
    
	/**
	 * O <code>DataSource</code> de mercadorias é armazenado na sessão do usuário.
	 * Esse método é responsável por recuperar esse objeto e deixá-lo pronto para uso.
	 * @return <code>MercadoriaDataSource</code> da sessão do usuário.
	 */
	public ClientDataSource getDataSource() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		ClientDataSource ds = (ClientDataSource) session.getAttribute("dsclient");
		if (ds == null) {
			ds = new ClientDataSource();
			ds.synch(dao.getAll());
			session.setAttribute("dsclient", ds);
		}
		return ds;
	}
	
	/**
	 * Configura um conversor para double em pt-BR, usado no campo de preço.
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Double.class, 
				new CustomNumberEditor(Double.class, NumberFormat.getInstance(new Locale("pt","BR")), true));
	}

}

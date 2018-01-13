package br.com.exemplo.titulo.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.exemplo.titulo.model.StatusTitulo;
import br.com.exemplo.titulo.model.Titulo;
import br.com.exemplo.titulo.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		//mv.addObject("todosStatusTitulo", StatusTitulo.values());		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		
		titulos.save(titulo);
	
		
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");	
			
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		List<Titulo> todosTitulos = titulos.findAll();
		mv.addObject("listaTitulos", todosTitulos);
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
	@RequestMapping("/{codigo}" )
	public ModelAndView editar(@PathVariable Long codigo) {
		
		Titulo titulo = titulos.getOne(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
		
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	private String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		titulos.delete(codigo);
		
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");	
		
		
		return "redirect:/titulos";
	}
	
	/*@CrossOrigin(origins = "*")
	@RequestMapping(value = "/boletos",  method = RequestMethod.GET)
	public ResponseEntity<Resource> boleto() throws FileNotFoundException {
		
				// Cedente
				Cedente cedente = new Cedente("FADESP - Fundação de Amparo e Desenvolvimento da Pesquisa", "10.687.566/0001-97");
				
				// Sacado
				Sacado sacado = new Sacado("Rayan Teixeira", "00864683243");
				
				// Endereço do sacado
				Endereco endereco = new Endereco();
				endereco.setUF(UnidadeFederativa.PA);
				endereco.setLocalidade("Ananindeua");
				endereco.setCep(new CEP("66645-000"));
				endereco.setBairro("Águas Lindas");
				endereco.setLogradouro("BR 316 - KM 05");
				endereco.setNumero("1010");
				
				sacado.addEndereco(endereco);
				
				// Criando o título
				ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_DO_BRASIL.create());
				contaBancaria.setAgencia(new Agencia(1674, "8"));
				contaBancaria.setNumeroDaConta(new NumeroDaConta(101739, "X"));
				//contaBancaria.setCarteira(new Carteira(17, TipoDeCobranca.COM_REGISTRO));
				contaBancaria.setCarteira(new Carteira(17));
				
				org.jrimum.domkee.financeiro.banco.febraban.Titulo titulo = new org.jrimum.domkee.financeiro.banco.febraban.Titulo(contaBancaria, sacado, cedente);
				titulo.setNumeroDoDocumento("0000000066");
				titulo.setNossoNumero("28588450000000066");
				titulo.setDigitoDoNossoNumero("7");
				
				titulo.setValor(BigDecimal.valueOf(100.00));
				titulo.setDataDoDocumento(new Date());
				
				Calendar calendar = Calendar.getInstance();
				calendar.set(2017, 12, 27);
				titulo.setDataDoVencimento(calendar.getTime());
				
				titulo.setTipoDeDocumento(TipoDeTitulo.DS_DUPLICATA_DE_SERVICO);
				
				titulo.setAceite(Aceite.N);
				
				// Dados do boleto
				Boleto boleto = new Boleto(titulo);
				boleto.setLocalPagamento("Pagar preferencialmente no Banco do Brasil");
				boleto.setInstrucaoAoSacado("Evite multas, pague em dias suas contas.");
				
				boleto.setInstrucao1("Após o vencimento, aplicar multa de 2,00% e juros de 1,00% ao mês");
				
				BoletoViewer boletoViewer = new BoletoViewer(boleto);
				File arquivoPdf = boletoViewer.getPdfAsFile("boletoBB.pdf");
				
				
				
			
				FileInputStream fis = new FileInputStream(arquivoPdf);
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        byte[] buf = new byte[1024];
		        try {
		            for (int readNum; (readNum = fis.read(buf)) != -1;) {
		                bos.write(buf, 0, readNum); //no doubt here is 0
		                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
		            }
		        } catch (IOException ex) {
		            Logger.getLogger(TituloController.class.getName()).log(Level.SEVERE, null, ex);
		        }
		       
		        */
		        
		        /*
		        
		        
		        // Converte o arquivo para array de bytes
		        byte[] b64 = Base64.getEncoder().encode(bos.toByteArray());
		        ByteArrayResource resource = new ByteArrayResource(b64);
		        
			        
		        
		        return ResponseEntity.ok()
		                .contentLength(b64.length)
		                .contentType(MediaType.parseMediaType("application/octet-stream"))
		                .body(resource);
		        
		        */
		       
		        //--------------------------mostra o pdf pra visualizar----------------------------//
		         
		       /* byte[] bytes = bos.toByteArray();
		 
		        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);  
		        		
				HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=boleto.pdf");
				return ResponseEntity
		    			 .ok()
		    			 .headers(headers)
		                 .contentType(MediaType.APPLICATION_PDF)
		                 .body(new InputStreamResource(bis));
		        
	
	}*/
	
	
	
}

package br.com.teste;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import java.nio.charset.StandardCharsets;
import static br.com.teste.Main.*;

public class EnviarBairooaction implements AcaoRotinaJava {
    private EntityFacade dwfFacade;

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {
        this.dwfFacade = EntityFacadeFactory.getDWFFacade();

        Registro[] registros = contextoAcao.getLinhas();
        String cep = " ";
        try {

            for (Registro registro : registros) {
                JapeWrapper tela1DAO = JapeFactory.dao("AD_TELATESTE1");
                DynamicVO idVO = tela1DAO.findByPK(registro.getCampo("ID"));
                cep = idVO.asBigDecimal("CEP").toPlainString();
                String respostaJson = consultarCEP(cep);
                String bairro = PegarBairro(respostaJson);

              JapeWrapper tela2DAO = JapeFactory.dao("AD_TELATESTE2");
                DynamicVO bairroTela2VO = tela2DAO.findOne("BAIRRO = ?", bairro);

            if(bairroTela2VO != null){
             contextoAcao.setMensagemRetorno("Bairro ja cadastrado na tela de bairros");   
            }else{

            tela2DAO.create()
            .set("BAIRRO", bairro)
            .save();
            contextoAcao.setMensagemRetorno("Bairro do  " + cep + "Registrado com Sucesso");
               
            }else{
                 contextoAcao.setMensagemRetorno("Bairro ja cadastrado na tela de bairros");

            }
                
            }
        } catch (Exception e) {
            contextoAcao.mostraErro("Erro no processo: " + e.getMessage());


        }
    }
}

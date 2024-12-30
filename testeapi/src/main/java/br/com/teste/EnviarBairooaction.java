
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.wrapper.JapeFactory;
import com.sankhya.util.Base64Impl;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EnviarBairooaction implements AcaoRotinaJava {
    private EntityFacade dwfFacade;

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {
        this.dwfFacade = EntityFacadeFactory.getDWFFacade();

        Registro[] registros = contextoAcao.getLinhas()[0];
    Try{
         for (Registro registro : registros) {
            JapeWrapper tela1DAO = JapeFactory.dao("AD_TELATESTE1");
            DynamicVO idVO = tela1DAO.findByPK(registro.getCampo("ID"));
            String cep = String.avlueOf(idVO.asBigdecimal("CEP"));
            String cep = idVO.asBigDecimal("CEP").toPlainString();
            String respostaJson = consultarCEP(cep);
            String bairro = PegarBirro(respostaJson);

            JapeWrapper tela2DAO = JapeFactory.dao("AD_TELATESTE2");
            String bairro2 = tela2DAO.asString("BAIRRO");
            if(bairro.equals(bairro2)){
             contextoAcao.setMensagemRetorno("Bairro ja cadastrado na tela de bairros");   
            }else{

            tela2DAO.create()
            .set("BAIRRO", bairro)
            .save();
            }
         }
         contextoAcao.setMensagemRetorno("Bairro do  " + cep "Registrado com Sucesso" );
        }catch (Exception e){
               contextoAcao.addMessage("Erro no processo: " + e.getMessage());

            }

}

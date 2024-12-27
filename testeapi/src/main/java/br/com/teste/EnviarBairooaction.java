import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.wrapper.JapeFactory;

public class EnviarBairroaction implements AcaoRotinaJava {
    private EntityFacade dwfFacade;

    @Override
    public void doAction(ContextoAcao contextoAcao) throws Exception {
        this.dwfFacade = EntityFacadeFactory.getDWFFacade();

        Registro[] registros = contextoAcao.getLinhas();

         for (Registro registro : registros) {
            JapeWrapper Tela1DAO = JapeFactory.dao("AD_TELATESTE1");
            DynamicVO idVO = Tela1DAO.findByPK(registro.getCampo(ID));
            Int cep = idVO.asInt("CEP");
            String respostaJson = consultarCEP(cep);
            String bairro = PegarBirro(respostaJson);
            JapeWrapper Tela2DAO = JapeFactory.dao("AD_TELATESTE2");
            tela2DAO.create()
            .set("BAIRRO", bairro)
            .save();
         }


         }

}
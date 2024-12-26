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


       Registro[] registros = contexto.getLinhas();
        for(Registro registro : registros){
            DynamicVO cabVO = JapeFactory.dao(DynamicEntityNames.CABECALHO_NOTA).findByPK(registro.getCampo("NUNOTA"));

}
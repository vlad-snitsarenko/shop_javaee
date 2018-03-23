package by.epam.soapclient;


public class SoapClient {
    SoapPriceService_Service service = new SoapPriceService_Service();
    SoapPriceService server = service.getSoapPriceServicePort();

    public double getPriceByID(int id) {
        Price p = new Price();
        try {
            p = server.getPriceByID(id);
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return p.getValue();
    }

}

package example;

import io.runon.commons.config.Config;
import io.runon.cryptocurrency.exchanges.binance.account.BinanceFuturesAccount;

/**
 * @author macle
 */
public class AccountTest {
    public static void main(String[] args)  {
        String symbol = "BTCUSDT";

        BinanceFuturesAccount account = new BinanceFuturesAccount(Config.getConfig("binance.api.key"),Config.getConfig("binance.secret.key"));
//        MarketOrderTrade marketPriceOrder = account.orderCash("BTCUSDT", Trade.Type.BUY, new BigDecimal(50));
//        System.out.println(marketPriceOrder.getTradeType() + " " + marketPriceOrder.getTradePrice() + " " +marketPriceOrder.getQuantity() );
//
//        System.out.println(account.getCash());


//        try{account.closePosition(symbol);}catch (Exception e){e.printStackTrace();}
    }
}

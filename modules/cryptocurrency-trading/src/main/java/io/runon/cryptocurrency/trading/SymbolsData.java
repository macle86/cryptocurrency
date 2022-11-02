package io.runon.cryptocurrency.trading;

import io.runon.trading.CandleTimes;
import io.runon.trading.symbol.SymbolInteger;

import java.time.ZoneId;
import java.util.*;

/**
 * 심볼을 사용하는 데이터 내리기 기본 메소드 정리
 * @author macle
 */
public abstract class SymbolsData {

    protected String [] symbols;

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }

    protected String outDirPath;

    public void setOutDirPath(String outDirPath) {
        this.outDirPath = outDirPath;
    }

    protected ZoneId zoneId = CandleTimes.UTC_ZONE_ID;

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    protected long startOpenTime = -1;

    public void setStartOpenTime(long startOpenTime) {
        this.startOpenTime = startOpenTime;
    }



    public void setSymbolsMarket(String market) {

        String [] allSymbols = getAllSymbols();
        List<String> symbolList = new ArrayList<>();

        for (String symbol : allSymbols) {
            if(symbol.endsWith(market)){
                String leftSymbol = symbol.substring(0, symbol.length() - market.length());
                if(Markets.isMarketIndexSymbol(symbol)){
                    symbolList.add(symbol);
                }
            }
        }
        symbols = symbolList.toArray(new String[0]);
        symbolList.clear();
    }

    public void setSymbolsMarket(String market, Map<String, Integer> rankingMap ) {

        if(rankingMap == null){
            setSymbolsMarket(market);
            return;
        }

        String [] allSymbols = getAllSymbols();
        List<SymbolInteger> symbolList = new ArrayList<>();

        for (String symbol : allSymbols) {
            if(symbol.endsWith(market)){
                String leftSymbol = symbol.substring(0, symbol.length() - market.length());
                if(Markets.isMarketIndexSymbol(symbol)){
                    SymbolInteger symbolInteger = new SymbolInteger();
                    symbolInteger.setSymbol(symbol);
                    Integer ranking = rankingMap.get(leftSymbol);
                    symbolInteger.setNumber(Objects.requireNonNullElse(ranking, Integer.MAX_VALUE));
                    symbolList.add(symbolInteger);
                }
            }
        }

        setSymbols(symbolList);
    }

    public void setSymbolsMarket(String [] markets) {
        String [] allSymbols = getAllSymbols();
        List<String> symbolList = new ArrayList<>();

        for (String symbol : allSymbols) {
            for(String market : markets){
                if(symbol.endsWith(market)){
                    String leftSymbol = symbol.substring(0, symbol.length() - market.length());
                    if(Markets.isMarketIndexSymbol(symbol)){
                        symbolList.add(symbol);
                        break;
                    }
                }
            }
        }
        symbols = symbolList.toArray(new String[0]);
        symbolList.clear();
    }

    public void setSymbolsMarket(String [] markets, String [] simpleSymbols) {
        List<String> symbolList = new ArrayList<>();

        for (String symbol : simpleSymbols) {
            for(String market : markets){
                symbolList.add(symbol+market);
            }
        }
        symbols = symbolList.toArray(new String[0]);
        symbolList.clear();
    }


    public void setSymbolsMarket(String [] markets, Map<String, Integer> rankingMap ) {
        if(rankingMap == null){
            setSymbolsMarket(markets);
            return;
        }

        String [] allSymbols = getAllSymbols();
        List<SymbolInteger> symbolList = new ArrayList<>();

        for (String symbol : allSymbols) {
            for(String market : markets){
                if(symbol.endsWith(market)){
                    String leftSymbol = symbol.substring(0, symbol.length() - market.length());
                    if(Markets.isMarketIndexSymbol(symbol)){
                        SymbolInteger symbolInteger = new SymbolInteger();
                        symbolInteger.setSymbol(symbol);
                        Integer ranking = rankingMap.get(leftSymbol);
                        symbolInteger.setNumber(Objects.requireNonNullElse(ranking, Integer.MAX_VALUE));
                        symbolList.add(symbolInteger);
                        break;
                    }
                }
            }
        }

        setSymbols(symbolList);
    }

    protected void setSymbols( List<SymbolInteger> symbolList ){
        SymbolInteger [] symbolIntegers = symbolList.toArray(new SymbolInteger[0]);
        Arrays.sort(symbolIntegers, SymbolInteger.SORT);

        symbolList.clear();

        String [] symbols = new String[symbolIntegers.length];
        for (int i = 0; i <symbols.length ; i++) {
            symbols[i] = symbolIntegers[i].getSymbol();
        }

        this.symbols = symbols;
    }

    public abstract String [] getAllSymbols();

}

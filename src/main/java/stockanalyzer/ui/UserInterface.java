package stockanalyzer.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.downloader.ParallelDownloader;
import stockanalyzer.downloader.SequentialDownloader;

public class UserInterface
{

	private Controller ctrl = new Controller();
	private SequentialDownloader singledownloader = new SequentialDownloader();
	private ParallelDownloader multidownloader = new ParallelDownloader();

	public void getDataFromCtrl1(){
		try{
			ctrl.process("CSCO");
		}		catch (YahooException y){
			System.out.println(y.getMessage());
		}
	}

	public void getDataFromCtrl2()  {
		try{
			ctrl.process("AAPL");
		}		catch (YahooException y){
			System.out.println(y.getMessage());
		}
	}

	public void getDataFromCtrl3(){
		try{
			ctrl.process("OMV.VI");
		}		catch (YahooException y){
			System.out.println(y.getMessage());
		}
	}

	public void getDataFromCtrl4(){
		try{
			ctrl.process("ATVI");
		}		catch (YahooException y){
			System.out.println(y.getMessage());
		}
	}

	public void getDataFromCtrl5(){
		try{
			ctrl.process("CSCO,AAPL,OMV.VI,ATVI");
		}		catch (YahooException y){
			System.out.println(y.getMessage());
		}
	}

	public void getDataForCustomInput() {
		try{
			ctrl.process(readLine());
		}		catch (YahooException y){
			System.out.println(y.getMessage());
		}

	}

	public void getDatafromDownloadtickers()
	{
		List<String> tickers = new ArrayList<>();
		Collections.addAll(tickers, "NKE","ADS.DE","0992.HK","NVDA","AMD");
		long startTime = System.currentTimeMillis();
		singledownloader.process(tickers);
		System.out.println("Runtime Single Thread Execution: " + (System.currentTimeMillis()-startTime) + "ms");
		startTime = System.currentTimeMillis();
		multidownloader.process(tickers);
		System.out.println("Runtime MultiThread Execution: " + (System.currentTimeMillis()-startTime) + "ms");



	}

	public void start(){
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Please choose from:");
		menu.insert("a", "Choice Cisco Systems, Inc.", this::getDataFromCtrl1);
		menu.insert("b", "Apple", this::getDataFromCtrl2);
		menu.insert("c", "OMV", this::getDataFromCtrl3);
		menu.insert("d", "Activision Blizzard, Inc.", this::getDataFromCtrl4);
		menu.insert("e", "Compare a,b,c und d.", this::getDataFromCtrl5);
		menu.insert("f", "Choice User Input:", this::getDataForCustomInput);
		menu.insert("g", "Download tickers",this::getDatafromDownloadtickers);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			choice.run();
		}
		System.out.println("Program finished");
	}

	protected String readLine()
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
			System.out.println("Your input is wrong!");
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit)
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class SerialRead {

	public static final void main(String[] args) {
		try {

			CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("/dev/tty.usbmodem14611");
			SerialPort port = (SerialPort) portID.open("SerialRead", 5000); // waiting5000ms

			port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

			InputStream is = port.getInputStream();
			OutputStream os = port.getOutputStream();

			int c;
			int count = 0;			
			/*
			while ((c = is.read()) != -1) {
				System.out.printf("%c ", c & 0xff);				
				if (count % 16  == 0) {
					System.out.println();
				}
				count++;
			}
			*/
			
			for (int i = 0; i < 10; ++i) {
				c = is.read();
				System.out.printf("%c ", c & 0xff);
			}
			os.write('z');			
			
			for (int i = 0; i < 10; ++i) {
				c = is.read();
				System.out.printf("%c ", c & 0xff);
			}
			
			
			
			
			
			is.close();
			port.close();
		} catch (NoSuchPortException e) {
			System.err.println("Can Not Find Device");
			e.printStackTrace();
		} catch (PortInUseException e) {
			System.err.println("Can Not Open Device");
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			System.err.println("Invalid Parameter");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

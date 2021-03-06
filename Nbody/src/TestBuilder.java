import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TestBuilder {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("src/test");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(81 + " ");
		bw.newLine();
		for (int i = 1; i <= 9; i++)
			for (int j = 1; j <= 9; j++){
				bw.write(10*i + " ");
				bw.write(10*j + " ");
				double vx = Math.random()*10 - 5;
				double vy = Math.random()*10 - 5;
				bw.write(vx + " " + vy + " " + 1 + " " + 1);
				bw.newLine();
			}
		bw.flush();
		bw.close();
		fw.close();
	}

}

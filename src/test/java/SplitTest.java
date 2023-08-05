
import org.junit.jupiter.api.Test;
import org.mitre.secretsharing.Part;
import org.mitre.secretsharing.Secrets;
import org.mitre.secretsharing.codec.PartFormats;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.MnemonicUtils;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.*;


class SplitTest {

	@Test
	void testSecret(){
		byte[] secret = "approaches".getBytes(StandardCharsets.UTF_8);
		Part[] parts = Secrets.split(secret, 200,2, new SecureRandom());

		String[] stringParts = new String[parts.length];

		for(int s = 0; s < parts.length; s++)
			stringParts[s] = PartFormats.currentStringFormat().format(parts[s]);


		for(int i = 0; i < stringParts.length; i++){
			for(int j = 0; j < stringParts.length; j++){
				if(j != i) {
					Part[] p = new Part[]{PartFormats.parse(stringParts[i]), PartFormats.parse(stringParts[j])};
					byte[] ss = Secrets.join(p);
					String secretString = new String(ss, StandardCharsets.UTF_8);
					assertThat(secretString).isEqualTo("approaches");
				}
			}
		}
	}

	@Test
	void sameMnemonic(){

			byte[] bytes = {
					0x23, 0x45, 0x67, 0x49, 0x33, 0x45, 0x21, 0x12,
					0x23, 0x45, 0x67, 0x49, 0x33, 0x45, 0x21, 0x12
			};

			//checking to see if the same Mnemonic is generated everytime with the same entropy
			for (int i = 0; i<500; i++)
				System.out.println(MnemonicUtils.generateMnemonic(bytes));
	}
}

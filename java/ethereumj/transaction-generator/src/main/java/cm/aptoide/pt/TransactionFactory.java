package cm.aptoide.pt;

import org.spongycastle.util.encoders.Hex;

import cm.aptoide.pt.ethereumj.Transaction;
import cm.aptoide.pt.ethereumj.util.ByteUtil;

public class TransactionFactory {

	private static long gasPrice = 50_000_000_000L;
	private static long gasLimit = 0xfffff;

	public Transaction createTransaction(byte[] nonce, byte[] gasPrice, byte[] gasLimit, byte[]
					receiverAddr, byte[] value, byte[] data, int chainIdForNextBlock) {
		return new Transaction(nonce, gasPrice, gasLimit, receiverAddr, value, data,
						chainIdForNextBlock);
	}

	public Transaction createTransaction(int nonce, String receiverAddr, int value, byte[] data, int
					chainIdForNextBlock) {
		return createTransaction(ByteUtil.intToBytesNoLeadZeroes(nonce),
						ByteUtil.longToBytesNoLeadZeroes(gasPrice), ByteUtil.longToBytesNoLeadZeroes(gasLimit),
						HexProxy.decode(receiverAddr), HexProxy.decode(Long.toHexString(value)),
						Hex.decode(data), chainIdForNextBlock);
	}
}
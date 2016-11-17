package hex.core.context

/**
 * Created by poundex on 15/11/16.
 */
public interface CalculatorContext
{
	void pushParsed(String stringToParse)
	void pushRaw(Object object)
	Object pop()

	Map<Integer, Object> getStackView()
	Map<Integer, Object> getListView()
}

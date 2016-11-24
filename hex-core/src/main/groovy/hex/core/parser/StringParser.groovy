package hex.core.parser

import hex.core.context.CalculatorContext
import hex.core.context.SymbolManager
import hex.core.engine.Symbol

/**
 * Created by poundex on 15/11/16.
 */
class StringParser
{
	private final FunctionResolver functionResolver
	private final SymbolManager symbolManager

	StringParser(FunctionResolver functionResolver, SymbolManager symbolManager)
	{
		this.functionResolver = functionResolver
		this.symbolManager = symbolManager
	}

	void parse(CalculatorContext calculatorContext, String inputString)
	{
		new Parser(calculatorContext, inputString).parse()
	}

	private class Parser
	{
		private final String inputString
		private final CalculatorContext calculatorContext

		Parser(CalculatorContext calculatorContext, String inputString)
		{
			this.inputString = inputString
			this.calculatorContext = calculatorContext
		}

		void parse()
		{
			if (inputString.contains(' '))
				inputString.split(' ').each(this.&doParse)
			else
				doParse(inputString)
		}

		private void doParse(String string)
		{
			if (isOperator(string))
				calculatorContext.pushRaw functionResolver.resolveFunction(string)

			else if (string.isNumber())
				calculatorContext.pushRaw string.toBigDecimal()

			else if (string ==~ /\$[a-zA-Z_]+/)
				calculatorContext.pushRaw new Symbol(string.substring(1), symbolManager)

			else if (string ==~ /\w+/)
				calculatorContext.pushRaw functionResolver.resolveFunction(string)
		}

		private boolean isOperator(String string)
		{
			return (string.length() < 2 && OPERATORS.contains(string))
		}

		private final static List<String> OPERATORS = [
				'+', '-', '*', '/', '=', '' , '<', '~'
		]
	}
}

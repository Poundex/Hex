package hex.core.parser

import hex.core.context.CalculatorContext
import hex.core.engine.*
import hex.core.util.AdditionOperator
import hex.core.util.DivisionOperator
import hex.core.util.DropStackCommand
import hex.core.util.DuplicateStackCommand
import hex.core.util.MultiplicationOperator
import hex.core.util.SubtractionOperator

/**
 * Created by poundex on 15/11/16.
 */
class StringParser
{
	private final FunctionResolver functionResolver

	StringParser(FunctionResolver functionResolver)
	{
		this.functionResolver = functionResolver
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
				calculatorContext.pushRaw OPERATORS[string].newInstance()

			else if (string.isNumber())
				calculatorContext.pushRaw string.toBigDecimal()

			else if (string ==~ /\w+/)
				calculatorContext.pushRaw functionResolver.resolveFunction(string)
		}

		private boolean isOperator(String string)
		{
			return (string.length() < 2 && OPERATORS.containsKey(string))
		}

		private final static Map<String, Class<? extends Actuator>> OPERATORS = [
				'+': AdditionOperator,
				'-': SubtractionOperator,
				'*': MultiplicationOperator,
				'/': DivisionOperator,

				'' : DuplicateStackCommand,
				'<': DropStackCommand,
		]
	}
}

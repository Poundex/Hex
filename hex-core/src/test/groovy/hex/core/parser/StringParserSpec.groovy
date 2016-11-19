package hex.core.parser

import hex.core.context.CalculatorContext
import hex.core.util.AdditionOperator
import hex.core.util.DuplicateStackCommand
import spock.lang.Specification

/**
 * Created by poundex on 16/11/16.
 */
class StringParserSpec extends Specification
{
	void "Numbers are numbers"(String input, Number result)
	{
		setup:
		CalculatorContext calculatorContext = Mock(CalculatorContext)

		when:
		new StringParser().parse(calculatorContext, input)

		then:
		1 * calculatorContext.pushRaw(result)

		where:
		input   | result
		"1"     | 1
		"1.0"   | 1.0
		"1.234" | 1.234
	}

	void "Multiple tokens are handled"()
	{
		setup:
		CalculatorContext calculatorContext = Mock(CalculatorContext)

		when:
		new StringParser().parse(calculatorContext, "2 3 4")

		then:
		1 * calculatorContext.pushRaw(2)
		1 * calculatorContext.pushRaw(3)
		1 * calculatorContext.pushRaw(4)
		0 * _
	}

	void "Operators are recognised"()
	{
		setup:
		CalculatorContext calculatorContext = Mock(CalculatorContext)

		when:
		new StringParser().parse(calculatorContext, "2 3 +")
		new StringParser().parse(calculatorContext, "")

		then:
		1 * calculatorContext.pushRaw(2)
		1 * calculatorContext.pushRaw(3)
		1 * calculatorContext.pushRaw(_ as AdditionOperator)
		1 * calculatorContext.pushRaw(_ as DuplicateStackCommand)
	}
}

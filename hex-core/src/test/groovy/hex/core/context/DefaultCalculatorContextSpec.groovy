package hex.core.context

import spock.lang.Specification

/**
 * Created by poundex on 15/11/16.
 */
class DefaultCalculatorContextSpec extends Specification
{
	void "basic stack tests"()
	{
		setup:
		CalculatorContext calculatorContext = new DefaultCalculatorContext()

		expect:
		calculatorContext.stackView.isEmpty()

		when:
		calculatorContext.pushRaw(1)

		then:
		with(calculatorContext.stackView) {
			size() == 1
			containsKey(0)
			get(0) == 1
		}

		when:
		calculatorContext.pushRaw(2)

		then:
		with(calculatorContext.stackView) {
			size() == 2
			keySet().containsAll([0, 1])
			get(0) == 2
			get(1) == 1
		}

		expect:
		calculatorContext.pop() == 2
		calculatorContext.stackView.size() == 1
		calculatorContext.pop() == 1
		calculatorContext.stackView.isEmpty()
	}
}

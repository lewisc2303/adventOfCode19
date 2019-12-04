package day2

import day2.Part1.getInputList

import scala.annotation.tailrec
import scala.util.{Success, Try}

object Part2 extends App {

  /*
	--- Part Two ---
"Good, the new computer seems to be working correctly!
Keep it nearby during this mission - you'll probably use it again.
Real Intcode computers support many more features than your new one,
but we'll let you know what they are as you need them."

"However, your current priority should be to complete your gravity assist around the Moon.
For this mission to succeed, we should settle on some terminology for the parts you've already built."

Intcode programs are given as a list of integers; these values are used as the initial state for the computer's memory.
When you run an Intcode program, make sure to start by initializing memory to the program's values.
A position in memory is called an address (for example, the first value in memory is at "address 0").

Opcodes (like 1, 2, or 99) mark the beginning of an instruction.
The values used immediately after an opcode, if any, are called the instruction's parameters.
For example, in the instruction 1,2,3,4, 1 is the opcode; 2, 3, and 4 are the parameters.
The instruction 99 contains only an opcode and has no parameters.

The address of the current instruction is called the instruction pointer; it starts at 0.
After an instruction finishes, the instruction pointer increases by the number of values in the instruction;
until you add more instructions to the computer, this is always 4 (1 opcode + 3 parameters) for the add and multiply instructions.
(The halt instruction would increase the instruction pointer by 1, but it halts the program instead.)

"With terminology out of the way, we're ready to proceed.
To complete the gravity assist, you need to determine what pair of inputs produces the output 19690720."

The inputs should still be provided to the program by replacing the values at addresses 1 and 2, just like before.
In this program, the value placed in address 1 is called the noun, and the value placed in address 2 is called the verb.
Each of the two input values will be between 0 and 99, inclusive.

Once the program has halted, its output is available at address 0, also just like before.
Each time you try a pair of inputs, make sure you first reset the computer's memory to the values in the program (your puzzle input) -
in other words, don't reuse memory from a previous attempt.

Find the input noun and verb that cause the program to produce the output 19690720. What is 100 * noun + verb?
(For example, if noun=12 and verb=2, the answer would be 1202.)
	 */

  lazy val path = Try(getClass.getClassLoader.getResource("day2Input.txt").getFile)

  val TARGET_VALUE = 19690720

  val ADD       = 1
  val MULTIPLY  = 2
  val TERMINATE = 99

  def intCodeComputer(input: List[Int]): Option[Int] = {
    @tailrec
    def go(input: List[Int], toDrop: Int, memory: List[Int]): Option[Int] = {
      input match {
        case ADD :: x2 :: x3 :: x4 :: _ =>
          val newMemory = memory.updated(x4, memory(x2) + memory(x3))
          println(s"[+ Updated $x4 with ${memory(x2) + memory(x3)} ] " + memory)
          go(newMemory.drop(toDrop), toDrop + 4, newMemory)
        case MULTIPLY :: x2 :: x3 :: x4 :: _ =>
          val newMemory = memory.updated(x4, memory(x2) * memory(x3))
          println(s"[* Updated $x4 with ${memory(x2) + memory(x3)}] " + memory)
          go(newMemory.drop(toDrop), toDrop + 4, newMemory)
        case TERMINATE :: _ =>
          println("[Terminated result] " + memory)
          Some(memory(0))
        case x =>
          println(s"WOOOOPS! YOU GONE AND MESSED UP for input: $x")
          None
      }
    }
    go(input, 0, input)
  }

  type Noun = Int
  type Verb = Int

  def getNounAndVerb(input: List[Int]): Option[(Int, Int)] = {
    @tailrec
    def go(noun: Noun, verb: Verb, input: List[Int]): Option[(Int, Int)] = {
      noun match {
        case 99 =>
          println("SOMETHING IS FUK'D")
          None
        case _ =>
          verb match {
            case 99 => go(noun + 1, 0, input)
            case _ =>
              val newInput = input.updated(1, noun).updated(2, verb)
              Try(intCodeComputer(newInput)) match {
                case Success(Some(x)) =>
                  if (x == TARGET_VALUE) {
                    println(s"The noun and verb = ($noun, $verb)")
                    Some(noun, verb)
                  } else go(noun, verb + 1, input)
                case _ => go(noun, verb + 1, input)
              }
          }
      }
    }

    go(0, 0, input)
  }

  val getList = getInputList(path).toList

  getNounAndVerb(getList)
}

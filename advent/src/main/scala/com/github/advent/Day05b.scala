package com.github.advent

import scala.collection.mutable.Stack

object Day05b {

  def main(args: Array[String]): Unit = {
    Console.out.println("day05a...")

    val lines = data.split("\n").toList.reverse

    // cheat a little
    var ss = for( i <- 0 until 9 ) yield ( new Stack[Char]() )

    var stacks = parse( lines, ss.toList )

    var is = datais.split('\n').toList

    is.foreach( i => {
      move(i, stacks)
      Console.println(stacks)
    })

    for( s <- stacks ) {
      Console.out.print(s.head)
    }

    Console.out.print('\n')
  }

  def move( line : String, stacks : List[Stack[Char]] ) : Unit = {

    Console.out.println(line.trim)

    val ps = line.trim.split( ' ' )
    val t = ps(1)
    val src = ps(3)
    val trg = ps(5)

    val ts = new Stack[Char]()

    for( i <- 0 until t.toInt ) {
      val c = stacks(src.toInt - 1).pop()
      ts.push(c)
    }

    while( ts.nonEmpty ) {
      stacks(trg.toInt - 1).push(ts.pop())
    }

  }

  def parse( lines : List[String], accum : List[Stack[Char]] ) : List[Stack[Char]] = {

    if( lines.isEmpty ) {
      accum
    }
    else {
      val h = lines.head.trim

      val cs = h.split(' ').toList.zipWithIndex

      for( c <- cs ) {
        if( c._1(1) != '0' ) {
          accum(c._2).push(c._1(1))
        }
      }


      parse( lines.tail, accum )
    }

  }

  var test = """[0] [D] [0]
                [N] [C] [0]
                [Z] [M] [P]"""

  var testis = """move 1 from 2 to 1
                 move 3 from 1 to 3
                 move 2 from 2 to 1
                 move 1 from 1 to 2"""

  var data = """[N] [0] [0] [0] [R] [0] [0] [0] [C]
                [T] [J] [0] [0] [S] [J] [0] [0] [N]
                [B] [Z] [0] [H] [M] [Z] [0] [0] [D]
                [S] [P] [0] [G] [L] [H] [Z] [0] [T]
                [Q] [D] [0] [F] [D] [V] [L] [S] [M]
                [H] [F] [V] [J] [C] [W] [P] [W] [L]
                [G] [S] [H] [Z] [Z] [T] [F] [V] [H]
                [R] [H] [Z] [M] [T] [M] [T] [Q] [W]"""
  
  var datais = """move 3 from 9 to 7
                 move 4 from 4 to 5
                 move 2 from 4 to 6
                 move 4 from 7 to 5
                 move 3 from 7 to 3
                 move 2 from 5 to 9
                 move 5 from 6 to 3
                 move 5 from 9 to 1
                 move 3 from 8 to 4
                 move 3 from 4 to 6
                 move 8 from 1 to 8
                 move 1 from 8 to 6
                 move 2 from 8 to 2
                 move 5 from 8 to 4
                 move 1 from 8 to 1
                 move 6 from 6 to 4
                 move 1 from 7 to 9
                 move 5 from 1 to 7
                 move 1 from 1 to 2
                 move 2 from 9 to 8
                 move 6 from 4 to 9
                 move 1 from 6 to 8
                 move 3 from 2 to 7
                 move 4 from 2 to 8
                 move 4 from 9 to 3
                 move 6 from 5 to 4
                 move 7 from 8 to 1
                 move 10 from 4 to 1
                 move 12 from 1 to 5
                 move 1 from 4 to 9
                 move 1 from 2 to 3
                 move 2 from 9 to 1
                 move 1 from 9 to 3
                 move 1 from 6 to 7
                 move 1 from 9 to 1
                 move 3 from 1 to 3
                 move 9 from 5 to 9
                 move 2 from 2 to 7
                 move 2 from 7 to 4
                 move 3 from 9 to 4
                 move 7 from 5 to 7
                 move 5 from 1 to 3
                 move 2 from 4 to 5
                 move 1 from 4 to 6
                 move 1 from 6 to 9
                 move 4 from 9 to 2
                 move 12 from 7 to 9
                 move 2 from 4 to 9
                 move 6 from 5 to 9
                 move 3 from 7 to 6
                 move 12 from 9 to 6
                 move 5 from 9 to 1
                 move 1 from 7 to 6
                 move 14 from 6 to 1
                 move 20 from 3 to 5
                 move 5 from 9 to 5
                 move 3 from 2 to 8
                 move 1 from 6 to 4
                 move 1 from 9 to 2
                 move 1 from 4 to 6
                 move 1 from 2 to 6
                 move 16 from 1 to 5
                 move 1 from 2 to 1
                 move 12 from 5 to 6
                 move 1 from 8 to 4
                 move 29 from 5 to 1
                 move 5 from 6 to 9
                 move 20 from 1 to 3
                 move 4 from 1 to 3
                 move 11 from 3 to 8
                 move 1 from 4 to 3
                 move 4 from 9 to 8
                 move 7 from 1 to 8
                 move 2 from 3 to 2
                 move 2 from 6 to 7
                 move 1 from 9 to 8
                 move 10 from 3 to 5
                 move 1 from 6 to 1
                 move 1 from 7 to 2
                 move 3 from 1 to 2
                 move 6 from 2 to 4
                 move 2 from 6 to 3
                 move 4 from 6 to 5
                 move 1 from 6 to 2
                 move 1 from 2 to 9
                 move 6 from 5 to 2
                 move 1 from 9 to 3
                 move 24 from 8 to 7
                 move 1 from 4 to 8
                 move 5 from 5 to 4
                 move 1 from 4 to 8
                 move 1 from 8 to 7
                 move 2 from 8 to 9
                 move 1 from 9 to 7
                 move 6 from 2 to 4
                 move 10 from 3 to 7
                 move 3 from 5 to 3
                 move 1 from 9 to 8
                 move 3 from 3 to 8
                 move 4 from 8 to 7
                 move 1 from 4 to 6
                 move 1 from 6 to 4
                 move 13 from 4 to 3
                 move 17 from 7 to 6
                 move 1 from 6 to 3
                 move 2 from 4 to 8
                 move 3 from 7 to 5
                 move 14 from 6 to 7
                 move 1 from 5 to 9
                 move 1 from 5 to 9
                 move 2 from 6 to 7
                 move 1 from 5 to 1
                 move 1 from 1 to 6
                 move 1 from 9 to 3
                 move 29 from 7 to 4
                 move 10 from 4 to 3
                 move 6 from 7 to 5
                 move 1 from 6 to 5
                 move 1 from 9 to 7
                 move 1 from 7 to 2
                 move 4 from 3 to 2
                 move 1 from 2 to 9
                 move 1 from 8 to 5
                 move 11 from 3 to 4
                 move 24 from 4 to 7
                 move 2 from 2 to 5
                 move 10 from 3 to 2
                 move 6 from 2 to 1
                 move 5 from 4 to 7
                 move 1 from 9 to 2
                 move 3 from 5 to 1
                 move 1 from 4 to 6
                 move 4 from 2 to 3
                 move 5 from 5 to 7
                 move 2 from 5 to 3
                 move 32 from 7 to 5
                 move 16 from 5 to 1
                 move 1 from 1 to 2
                 move 3 from 2 to 9
                 move 1 from 8 to 6
                 move 3 from 7 to 6
                 move 1 from 2 to 4
                 move 5 from 6 to 8
                 move 5 from 8 to 6
                 move 2 from 9 to 3
                 move 1 from 7 to 5
                 move 9 from 5 to 4
                 move 1 from 9 to 1
                 move 2 from 3 to 1
                 move 4 from 3 to 6
                 move 1 from 3 to 8
                 move 6 from 4 to 6
                 move 6 from 5 to 9
                 move 1 from 9 to 6
                 move 1 from 5 to 1
                 move 1 from 5 to 4
                 move 1 from 3 to 6
                 move 1 from 8 to 3
                 move 1 from 4 to 2
                 move 1 from 2 to 3
                 move 17 from 6 to 4
                 move 4 from 1 to 8
                 move 3 from 9 to 6
                 move 1 from 8 to 4
                 move 1 from 9 to 7
                 move 2 from 6 to 2
                 move 1 from 7 to 8
                 move 12 from 1 to 9
                 move 8 from 9 to 2
                 move 1 from 6 to 9
                 move 6 from 2 to 8
                 move 2 from 8 to 3
                 move 18 from 4 to 9
                 move 2 from 1 to 6
                 move 1 from 6 to 5
                 move 3 from 4 to 3
                 move 7 from 3 to 8
                 move 4 from 2 to 7
                 move 1 from 4 to 6
                 move 2 from 6 to 4
                 move 13 from 9 to 6
                 move 1 from 5 to 2
                 move 5 from 9 to 3
                 move 9 from 1 to 2
                 move 1 from 1 to 8
                 move 1 from 2 to 6
                 move 3 from 7 to 6
                 move 2 from 2 to 6
                 move 9 from 8 to 6
                 move 1 from 7 to 8
                 move 1 from 8 to 7
                 move 2 from 4 to 6
                 move 5 from 3 to 6
                 move 17 from 6 to 9
                 move 7 from 8 to 4
                 move 4 from 2 to 3
                 move 17 from 6 to 2
                 move 1 from 6 to 4
                 move 1 from 7 to 8
                 move 1 from 8 to 9
                 move 24 from 9 to 6
                 move 4 from 3 to 1
                 move 1 from 1 to 5
                 move 20 from 6 to 4
                 move 4 from 6 to 9
                 move 1 from 5 to 7
                 move 2 from 4 to 2
                 move 1 from 9 to 7
                 move 25 from 4 to 3
                 move 1 from 4 to 2
                 move 2 from 1 to 6
                 move 3 from 9 to 4
                 move 2 from 4 to 7
                 move 2 from 7 to 5
                 move 1 from 4 to 2
                 move 1 from 6 to 3
                 move 1 from 1 to 5
                 move 5 from 3 to 9
                 move 1 from 5 to 6
                 move 10 from 2 to 8
                 move 9 from 2 to 5
                 move 21 from 3 to 6
                 move 1 from 7 to 6
                 move 2 from 6 to 5
                 move 5 from 9 to 7
                 move 6 from 7 to 8
                 move 19 from 6 to 9
                 move 1 from 6 to 1
                 move 8 from 8 to 1
                 move 1 from 6 to 1
                 move 2 from 8 to 5
                 move 5 from 9 to 2
                 move 6 from 8 to 2
                 move 2 from 9 to 7
                 move 9 from 9 to 4
                 move 7 from 2 to 4
                 move 1 from 6 to 4
                 move 14 from 5 to 9
                 move 1 from 1 to 8
                 move 1 from 7 to 9
                 move 4 from 2 to 9
                 move 16 from 4 to 6
                 move 3 from 2 to 8
                 move 1 from 6 to 2
                 move 2 from 8 to 9
                 move 1 from 8 to 7
                 move 1 from 8 to 3
                 move 3 from 2 to 7
                 move 1 from 3 to 9
                 move 8 from 9 to 3
                 move 4 from 7 to 8
                 move 1 from 5 to 4
                 move 4 from 6 to 3
                 move 1 from 4 to 2
                 move 9 from 3 to 8
                 move 10 from 9 to 5
                 move 8 from 6 to 7
                 move 13 from 8 to 4
                 move 8 from 5 to 2
                 move 3 from 6 to 3
                 move 7 from 9 to 6
                 move 7 from 7 to 2
                 move 2 from 4 to 6
                 move 5 from 6 to 2
                 move 3 from 1 to 5
                 move 5 from 5 to 8
                 move 4 from 6 to 2
                 move 4 from 1 to 8
                 move 15 from 2 to 6
                 move 11 from 4 to 9
                 move 12 from 6 to 8
                 move 1 from 6 to 9
                 move 5 from 3 to 7
                 move 2 from 2 to 6
                 move 6 from 7 to 1
                 move 3 from 1 to 3
                 move 1 from 4 to 1
                 move 1 from 3 to 9
                 move 1 from 3 to 9
                 move 1 from 7 to 6
                 move 1 from 3 to 2
                 move 4 from 2 to 6
                 move 4 from 2 to 7
                 move 1 from 2 to 6
                 move 4 from 1 to 6
                 move 12 from 6 to 7
                 move 2 from 6 to 1
                 move 8 from 9 to 6
                 move 1 from 7 to 4
                 move 14 from 8 to 1
                 move 8 from 1 to 5
                 move 1 from 3 to 9
                 move 5 from 9 to 5
                 move 1 from 8 to 9
                 move 1 from 9 to 2
                 move 1 from 9 to 3
                 move 5 from 8 to 3
                 move 12 from 5 to 4
                 move 1 from 9 to 2
                 move 6 from 7 to 3
                 move 7 from 3 to 2
                 move 1 from 5 to 1
                 move 1 from 8 to 3
                 move 2 from 1 to 3
                 move 2 from 6 to 9
                 move 5 from 6 to 5
                 move 5 from 1 to 7
                 move 4 from 4 to 1
                 move 7 from 2 to 8
                 move 4 from 3 to 8
                 move 1 from 9 to 3
                 move 1 from 9 to 5
                 move 4 from 1 to 8
                 move 10 from 7 to 9
                 move 1 from 6 to 7
                 move 2 from 8 to 6
                 move 6 from 4 to 2
                 move 5 from 3 to 1
                 move 2 from 6 to 3
                 move 2 from 7 to 1
                 move 5 from 2 to 5
                 move 2 from 7 to 1
                 move 7 from 5 to 7
                 move 2 from 5 to 6
                 move 2 from 5 to 3
                 move 3 from 2 to 9
                 move 9 from 9 to 3
                 move 1 from 6 to 4
                 move 3 from 3 to 1
                 move 9 from 8 to 2
                 move 6 from 3 to 6
                 move 8 from 7 to 9
                 move 4 from 9 to 8
                 move 14 from 1 to 5
                 move 1 from 9 to 2
                 move 1 from 1 to 5
                 move 2 from 3 to 6
                 move 12 from 5 to 3
                 move 2 from 2 to 8
                 move 7 from 6 to 2
                 move 12 from 2 to 8
                 move 2 from 6 to 2
                 move 6 from 9 to 6
                 move 1 from 1 to 2
                 move 1 from 9 to 3
                 move 2 from 5 to 9
                 move 1 from 9 to 2
                 move 1 from 9 to 4
                 move 1 from 3 to 2
                 move 2 from 6 to 7
                 move 2 from 6 to 9
                 move 5 from 4 to 2
                 move 14 from 3 to 9
                 move 15 from 9 to 4
                 move 1 from 7 to 4
                 move 10 from 8 to 6
                 move 1 from 5 to 9
                 move 2 from 9 to 5
                 move 10 from 8 to 1
                 move 1 from 7 to 4
                 move 5 from 1 to 2
                 move 2 from 1 to 5
                 move 3 from 4 to 6
                 move 4 from 5 to 8
                 move 5 from 8 to 6
                 move 14 from 2 to 9
                 move 2 from 6 to 7
                 move 3 from 2 to 9
                 move 3 from 1 to 7
                 move 1 from 7 to 3
                 move 3 from 7 to 1
                 move 1 from 3 to 6
                 move 1 from 7 to 6
                 move 1 from 8 to 9
                 move 2 from 1 to 4
                 move 1 from 1 to 2
                 move 16 from 9 to 4
                 move 7 from 4 to 8
                 move 5 from 8 to 1
                 move 2 from 8 to 3
                 move 2 from 1 to 7
                 move 13 from 6 to 7
                 move 2 from 2 to 3
                 move 4 from 7 to 4
                 move 6 from 4 to 5
                 move 4 from 7 to 6
                 move 3 from 1 to 2
                 move 2 from 2 to 6
                 move 3 from 3 to 8
                 move 5 from 5 to 3
                 move 2 from 9 to 6
                 move 3 from 3 to 7
                 move 1 from 8 to 1
                 move 22 from 4 to 8
                 move 1 from 4 to 3
                 move 9 from 6 to 3
                 move 1 from 2 to 1
                 move 4 from 3 to 4
                 move 2 from 4 to 5
                 move 1 from 1 to 7
                 move 4 from 3 to 7
                 move 2 from 6 to 1
                 move 1 from 6 to 7
                 move 18 from 8 to 7
                 move 2 from 6 to 5
                 move 2 from 3 to 4
                 move 1 from 5 to 4
                 move 30 from 7 to 6
                 move 2 from 1 to 3
                 move 18 from 6 to 8
                 move 12 from 6 to 4
                 move 13 from 4 to 9
                 move 2 from 3 to 8
                 move 1 from 6 to 2
                 move 3 from 7 to 2
                 move 1 from 1 to 2
                 move 2 from 5 to 9
                 move 8 from 8 to 1
                 move 1 from 7 to 8
                 move 7 from 1 to 3
                 move 2 from 4 to 9
                 move 1 from 1 to 6
                 move 4 from 2 to 1
                 move 16 from 8 to 1
                 move 1 from 2 to 6
                 move 2 from 4 to 8
                 move 2 from 5 to 1
                 move 4 from 3 to 7
                 move 3 from 7 to 1
                 move 1 from 6 to 8
                 move 1 from 8 to 9
                 move 1 from 7 to 3
                 move 6 from 3 to 5
                 move 1 from 3 to 8
                 move 1 from 6 to 9
                 move 16 from 9 to 5
                 move 4 from 5 to 3
                 move 15 from 5 to 1
                 move 1 from 5 to 8
                 move 3 from 9 to 8
                 move 9 from 8 to 5
                 move 6 from 5 to 1
                 move 4 from 5 to 6
                 move 2 from 6 to 4
                 move 1 from 6 to 4
                 move 1 from 8 to 4
                 move 3 from 3 to 6
                 move 3 from 6 to 8
                 move 1 from 6 to 8
                 move 21 from 1 to 9
                 move 4 from 8 to 5
                 move 3 from 5 to 7
                 move 2 from 5 to 1
                 move 2 from 4 to 8
                 move 2 from 8 to 2
                 move 2 from 7 to 8
                 move 1 from 7 to 9
                 move 1 from 8 to 7
                 move 5 from 1 to 8
                 move 1 from 7 to 8
                 move 4 from 8 to 4
                 move 2 from 4 to 5
                 move 1 from 2 to 7
                 move 1 from 2 to 7
                 move 2 from 7 to 6
                 move 2 from 6 to 9
                 move 1 from 4 to 9
                 move 1 from 3 to 4
                 move 16 from 1 to 5
                 move 16 from 5 to 7
                 move 2 from 5 to 4
                 move 14 from 9 to 6
                 move 5 from 4 to 3
                 move 3 from 3 to 6
                 move 5 from 1 to 4
                 move 2 from 4 to 7
                 move 7 from 9 to 4
                 move 2 from 9 to 7
                 move 10 from 6 to 9
                 move 8 from 4 to 6
                 move 1 from 8 to 4
                 move 1 from 1 to 9
                 move 14 from 6 to 3
                 move 10 from 3 to 2
                 move 3 from 7 to 8
                 move 6 from 3 to 1
                 move 2 from 7 to 9
                 move 5 from 7 to 9
                 move 10 from 9 to 1
                 move 2 from 4 to 3
                 move 1 from 2 to 1
                 move 16 from 1 to 4
                 move 1 from 6 to 1
                 move 2 from 3 to 9
                 move 3 from 8 to 5
                 move 8 from 7 to 1
                 move 3 from 5 to 9
                 move 7 from 4 to 6
                 move 7 from 1 to 5
                 move 2 from 8 to 3
                 move 1 from 7 to 8"""
}

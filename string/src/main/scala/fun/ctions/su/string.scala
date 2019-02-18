package fun.ctions.su

import internal.{types => T}

object string {
  final case class Buffer[S](s: S)

  trait DropWord[A] {
    def dropWord(buf: A, pos: Int): String
  }

  object DropWord {
    def apply[A](implicit dw: DropWord[A]): DropWord[A] = dw

    implicit val stringBufferDropWord: DropWord[Buffer[String]] = (buf: Buffer[String], pos: Int) =>
      buf.s.drop(pos).dropRight(pos)
  }

  trait IResult[I, R]

  type Parser[A] = T.Parser[String, A, Buffer, IResult]
  type Result[R] = IResult[String, R]

  final case class Fail[R](i: String, xs: Seq[String], x: String) extends Result[R]
  final case class Partial[R](run: String => Result[R]) extends Result[R]
  final case class Done[R](i: String, r: R) extends Result[R]

  type Failure[R] = T.Failure[String, Buffer[String], R, IResult]

  type Success[A, R] = T.Success[String, Buffer[String], A, R, IResult]

  def failK[A]: Failure[A] = {
    case (t: Buffer[String], T.Pos(pos), _: T.More, stack: Seq[String], msg: String) =>
      Fail(DropWord[Buffer[String]].dropWord(t, pos), stack, msg)
  }

  def successK[A]: Success[A, A] = { case (t, T.Pos(pos), _, a) => Done(DropWord[Buffer[String]].dropWord(t, pos), a) }

  def parse[A](p: Parser[A], t: String): Result[A] =
    p.runParser[A](Buffer(t), T.Pos(0), T.Incomplete, failK, successK)
}

package fun.ctions.su.internal

object types {
  trait Parser[I, A, IS[_], IR[_, _]] {
    def runParser[R]: (
        IS[I],
        Pos,
        More,
        Failure[I, IS[I], R, IR],
        Success[I, IS[I], A, R, IR]
    ) => IR[I, A]
  }

  sealed trait More

  final case class Pos(fromPos: Int) extends AnyVal

  case object Complete extends More
  case object Incomplete extends More

  type Failure[I, T, R, IR[_, _]] = (T, Pos, More, Seq[String], String) => IR[I, R]

  type Success[I, T, A, R, IR[_, _]] = (T, Pos, More, A) => IR[I, R]
}

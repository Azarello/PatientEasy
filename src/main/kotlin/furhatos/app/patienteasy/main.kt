package furhatos.app.patienteasy

import furhatos.app.patienteasy.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class PatienteasySkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}

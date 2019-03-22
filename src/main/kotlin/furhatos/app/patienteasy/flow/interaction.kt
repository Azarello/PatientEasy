package furhatos.app.patienteasy.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.patienteasy.nlu.*
import java.util.*

val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("Hello and welcome to this module in which you will learn more about Intensive Short-Term " +
                "Dynamic Psychotherapy. The first step in developing a therapeutic alliance is to get the patient" +
                "to declare their internal emotional problem. Your task will be to do exactly this by " +
                "correctly identifying and blocking five defenses. Say yes if you would like to begin or no" +
                "if you want to hear the instructions again")
    }

    onResponse<Yes>{
        furhat.say("Great. This will be my first defense")
        goto(DeclareProblem)
    }

    onResponse<No>{
        furhat.say("Ok, restart me when you are ready to begin")
        goto(Idle)
    }
}


val DeclareProblem : State = state {

    val rand = Random()
    val num = rand.nextInt(3)
    onEntry {
        when (num) {
            0 -> goto(Vague1)
            1 -> goto(Denial1)
            2 -> goto(Projection1)
            3 -> goto(CoverWord1)
            4 -> goto(Rationalization1)
        }
    }
}


var counter1 = 0


val Counter1 : State = state {



    onEntry {
        counter1 += 1
        if (counter1 < 5) {
            furhat.say(" Let's move on to the next defense")
            goto(DeclareProblem) }
        else {
            furhat.say(" My problem is that I get very angry with dad at times when we talk")
            furhat.say(" Great job! You got the patient to declare their internal problem")
           /* goto(Resolution1) */
        }
    }
}

val Vague1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask("I suppose I've just been feeling kind of down lately")
            1 -> furhat.ask("I've been having these episodes for a while that are sort of a problem")
            2 -> furhat.ask("It's something to do with my father, just stuff going on that's complicated")
            3 -> furhat.ask("It's just these things happening in my life you know")
            4 -> furhat.ask("Some issues have been coming up that I'm not sure about")
        }
    }

    onResponse<VagueBlock1> {

        it.intent.specific
        it.intent.avoid
        it.intent.notice
        it.intent.negative
        it.intent.person
        it.intent.problem
        it.intent.feel

        when (num) {
            0 -> furhat.say("Correct. Vague answers prevent the patient and therapist from getting a clear picture " +
                    "of the problem, inhibiting the therapeutic process.")
            1 -> furhat.say("Great. A good way of blocking vagueness is by explaining to the patient " +
                    "how they are avoiding the issue and encouraging them to be more specific.")
            2 -> furhat.say("Yes. Vague defenses can often by identified because the patient does not provide any " +
                    "specific details about their problem. They paint with a wide brush without providing " +
                    "much information.")
            3 -> furhat.say("Exactly right. Expressions like “something” or “stuff going on” are indicators that " +
                    "the patient is being vague about expressing their problem.")
            4 -> furhat.say("Good job. Instead of settling for the vague statement “some issues have been coming up”, " +
                    "it is helpful to block the vagueness by asking the patient to be more specific about this.")

        }



        goto(Counter1)
    }

    onResponse<Yes> {
        furhat.say("Let's try again")
        goto(DeclareProblem)
    }

    onResponse<No> {
        furhat.say("It was a vague defense")
        goto(DeclareProblem)
    }
}



val Denial1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask(" Actually I don't have a problem that merits so much attention, not sure what I'm doing here")
            1 -> furhat.ask(" It's really not a big deal, I'm not bothered by it most of the time")
            2 -> furhat.ask(" I'm not really feeling all that bad when I think about it, it's just a minor thing")
            3 -> furhat.ask(" It seemed a lot worse before, now it doesn't strike me as very troubling or important")
            4 -> furhat.ask(" I've just been a little off, but it's not really a major issue")
        }
    }

    onResponse<DenialBlock1> {

        it.intent.deny
        it.intent.feel
        it.intent.notice
        it.intent.specific
        it.intent.problem
        it.intent.avoid

        when (num) {
            0 -> furhat.say("Yes. There are different kinds of denials, which all have to do with the " +
                    "patient in one way or another denying the reality of their emotional problem.")
            1 -> furhat.say(" Correct.One way of dealing with denial defenses is by pointing out to the " +
                    "patient that after all they came to a therapist so there must be something going on.")
            2 -> furhat.say("Excellent. This kind of denial is often referred to as minimization, in which the " +
                    "patient acknowledges they have a problem but downplays its significance and impact.")
            3 -> furhat.say("Good job. Denial can take the shape of a patient denying the stimulus of a problem, " +
                    "such as a conflict with a parent, or by denying the emotional distress itself.")
            4 -> furhat.say("Exactly right. In this case it is helpful to focus in on the “minor thing” to " +
                    "see if they are denying its importance and significance in their life.  ")
        }


        goto(Counter1)
    }

    onResponse<Yes> {
        furhat.say("Let's try again")
        goto(DeclareProblem)
    }

    onResponse<No> {
        furhat.say("That was a denial")
        goto(DeclareProblem)
    }
}

val Projection1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask(" Well I don't think it's a problem, my wife made me come here")
            1 -> furhat.ask(" Actually it was my doctor's idea for me to come here after my medication didn't help")
            2 -> furhat.ask(" You sure seem to think I have a problem that needs attention")
            3 -> furhat.ask(" My son thought I should see someone so I'm really doing it for his sake")
            4 ->furhat.ask("If my dad weren't annoying I wouldn't need to come here, it's really him with the issues")
        }
    }

    onResponse<ProjectionBlock1> {

        it.intent.person
        it.intent.problem
        it.intent.avoid
        it.intent.will
        it.intent.force
        it.intent.intellect
        it.intent.obey
        it.intent.specify
        it.intent.feel
        it.intent.notice

        when (num) {
            0 -> furhat.say("Excellent. In projection a patient may for example blame others as the cause of their " +
                    "problem, or claim that only other people believe the patient has a problem.")
            1 -> furhat.say("Perfect. As with other defenses, one way of blocking projection is to simply point out the" +
                    "defense explicitly to the patient. Ask them how they think without reference to others.")
            2 -> furhat.say(" Yes that's projection. As is shown in this example, it is not uncommon for patients " +
                    "to project on to the therapist. The patient does not think he has a problem, only the therapist does.")
            3 -> furhat.say("Good job. Projection can often be identified when patients make reference to other people" +
                    "without going into their own emotional state")
            4 -> furhat.say(" Exactly right. This example shows an example of how the patient projects his problem" +
                    "as in fact being his fathers issue. ")
        }

        goto(Counter1)
    }

    onResponse<Yes> {
        furhat.say("Let's try again")
        goto(DeclareProblem)
    }

    onResponse<No> {
        furhat.say("That was a projection")
        goto(DeclareProblem)
    }
}


val CoverWord1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask("I've been feeling a bit infuriated about my dad")
            1 -> furhat.ask(" It's just kind of annoying when he does the same thing all the time")
            2 -> furhat.ask(" My dad has this way of making me slightly upset when we talk")
            3 -> furhat.ask(" There are these situations when I feel rather disconcerted")
            4 -> furhat.ask(" He has a way of talking that's just rather bothersome and gets to me")
        }
    }

    onResponse<CoverWordBlock1> {

        it.intent.person
        it.intent.cover
        it.intent.negative
        it.intent.feel
        it.intent.notice
        it.intent.specify

        when (num) {
            0 -> furhat.say(" Yes this is a cover word defense. Cover words are words of weaker emotional" +
                    "strenght than the patient is actually feeling.")
            1 -> furhat.say(" Correct. 'Kind of annoying' is a cover word in this case. Clearly the patient" +
                    "must be feeling something stronger since they are unlikely to seek therapy for slight annoyance.")
            2 -> furhat.say(" Cover word is exactly right. Blocking cover words can be done by illuminating the " +
                    "particular word to the patient and inviting them to share more of their emotions")
            3 -> furhat.say(" Cover words can often be identified because they don't give weight enough to the" +
                    "situation or seem contrived. The word disconcerted used in this example really sticks out.")
            4 -> furhat.say(" Like other defenses, cover words are used to distance the patient from their" +
                    "actual painful emotions. By inviting them to use more appropriate words they may feel more" +
                    "comfortable exploring their feelings and vice versa.")
        }
        goto(Counter1)
    }


    onResponse<Yes> {
        furhat.say("Let's try again")
        goto(DeclareProblem)
    }

    onResponse<No> {
        furhat.say("That was a cover word block")
        goto(DeclareProblem)
    }
}


val Rationalization1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask(" If my dad just didn't bring up certain topics I wouldn't get upset")
            1 -> furhat.ask(" I think the reason for my problem stems from when I was younger")
            2 -> furhat.ask(" I think it's because he has this strange voice that I get needlessly upset")
            3 -> furhat.ask(" It's only when I'm tired that I experience these problems so I just need to get more sleep")
            4 -> furhat.ask(" I noticed that my problem comes up whenever I'm also stressed from work so that might be a reason")
        }
    }

    onResponse<RationalizationBlock1> {

        it.intent.intellect
        it.intent.feel
        it.intent.notice
        it.intent.avoid
        it.intent.person
        it.intent.negative
        it.intent.problem

        when (num) {
            0 -> furhat.say(" Yes correct. Rationalization means the patient describes reasons for their" +
                    "problem rather than the experience of their emotions")
            1 -> furhat.say("Yes this is rationalization. By analyzing the underlying reasons for their " +
                    "feeling rather than exploring the emotions directly, the patient is distancing himself from" +
                    "the problem.")
            2 -> furhat.say(" Excellent. Rationalization can often be identified by words like 'think', 'reason'" +
                    " or 'because' so they are a good hint the patient is rationalizing.")
            3 -> furhat.say(" Exactly right. Keep in mind that even if the patients analysis of the problem" +
                    "is reasonable or correct, they are nevertheless distancing themselves from their emotions")
            4 -> furhat.say(" Good job. In order to block rationalization, help the patient differentiate " +
                    "their reasons from feelings and then help them explore the feelings directly")
        }
        goto(Counter1)
    }

    onResponse<Yes> {
        furhat.say("Try again")
    }

    onResponse<No> {
        furhat.say("It was rationalization")
    }
}


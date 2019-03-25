package furhatos.app.patienteasy.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.patienteasy.nlu.*
import org.apache.logging.log4j.core.async.JCToolsBlockingQueueFactory
import org.netlib.util.Second
import java.util.*

val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("Hello and welcome to this program in which you will learn more about Intensive Short-Term " +
                "Dynamic Psychotherapy. The first step in the therapeutic process is to establish a conscious " +
                "therapeutic alliance. The following program will guide you through different modules that train " +
                "the skills necessary to establishing this alliance. When you are ready to start the first module, " +
                " say start. ")

    }

    onResponse<Continue>{
        goto(FirstModule)
    }

    onResponse<No>{
        furhat.say("Ok, restart me when you are ready to begin")
        goto(Idle)
    }
}

val FirstModule1 : State = state {
    onEntry {
        goto(FirstModule)
    }
}

val FirstModule : State = state {
    onEntry {
        furhat.ask("The first step in establishing a conscious therapeutic alliance is to get the patient to " +
                "declare an internal emotional problem, meaning they explicitly state an issue they are struggling" +
                " with. It is important for both patient and therapist to have a clear " +
                " picture of the issue to start therapy. Patients are often reluctant to explicitly state their " +
                "issue immediately, and use various defense mechanisms to avoid talking about the problem. " +
                "In this module you will learn to" +
                " identify and block such defenses. The module is complete once you have correctly identified and " +
                "blocked five defenses. Say continue if you are ready to start, or repeat if you would like to hear the " +
                "instruction again")
    }



    onResponse<Continue> {
        furhat.say("Ok, this will be the first defense.")
        goto(DeclareProblem)
    }

    onResponse<Repeat> {
        goto(FirstModule1)
    }
}

val DeclareProblem : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
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
            furhat.say("My problem is that I get very angry with my father sometimes when we speak")
            goto(Resolution1)

        }
    }
}
val Wait1 : State = state {

    onEntry {
        furhat.ask("When you are ready for the next defense, say next")
    }

    onResponse<Continue> {
        goto(Counter1)
    }
}

val Vague1 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)
    onEntry {
        when (num) {
            0 -> furhat.ask("I've just been feeling kind of down lately and not sure what is the matter")
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



        goto(Wait1)
    }

    onResponse<TryAgain> {
        furhat.say("Let's try another defense")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("It was a vague defense. They can often be identified because they don't give a clear or" +
                " specific explanation of a problem, but rather ambiguous and unclear references.  ")
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


        goto(Wait1)
    }

    onResponse<TryAgain> {
        furhat.say("Let's try again with another defense")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("That was a denial. You can often identify it because the patient refuses to talk about their " +
                " problem. Either they don't want to mention what is causing it, or the troubling emotion itself. ")
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
            0 -> furhat.say("Excellent. In projection a patient may blame others as the cause of their " +
                    "problem, or ignore their issue by claiming that only other people believe the patient has a problem.")
            1 -> furhat.say("Perfect. As with other defenses, one way of blocking projection is to simply point out the" +
                    "defense explicitly to the patient. Ask them how they think without reference to others.")
            2 -> furhat.say(" Yes that's projection. As is shown in this example, it is not uncommon for patients " +
                    "to project on to the therapist. The patient does not think he has a problem, only the therapist does.")
            3 -> furhat.say("Good job. Projection can often be identified when patients make reference to other people" +
                    "without going into their own emotional state")
            4 -> furhat.say(" Exactly right. This example shows an example of how the patient projects his problem" +
                    "as in fact being his fathers issue. ")
        }

        goto(Wait1)
    }

    onResponse<TryAgain> {
        furhat.say("No problem. Let's try another defense instead.")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("That was a projection. Whenever the patient mentions other people and not their own feelings" +
                " it is a good sign that they may be projecting.")
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
                    "import than the patient is actually feeling.")
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
        goto(Wait1)
    }


    onResponse<TryAgain> {
        furhat.say("Ok, this one was pretty hard. I will give you another defense for now.")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("That was a cover word block. When identifying this defense, look for words that express weak" +
                " emotional content. Oftentimes patients may use unusual adjectives as covers so that's a good hint.")
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
        goto(Wait1)
    }

    onResponse<TryAgain> {
        furhat.say("Sure. Let's go with another defense, I'm sure you will get this one next time.")
    }

    onResponse<GiveAnswer> {
        furhat.say("It was rationalization. Whenever patients provide reasons for their problem rather than" +
                " talking directly about their emotions they may be rationalizing. ")
    }
}


val Resolution1 : State = state {

    onEntry {
        furhat.ask(" Great job! You got the patient to declare an internal problem which is the anger they " +
                "experience when talking to their father. You have successfully completed the first module and first " +
                "part of establishing a therapeutic alliance. If you would like to go over this module again say repeat. " +
                "If you would like to continue to the next module say continue.")
    }

    onResponse<Repeat> {
        counter1 = 0
        goto(FirstModule)
    }

    onResponse<Continue> {
        goto(SecondModule)
    }
}

val SecondModule1 : State = state {
    onEntry {
        goto(SecondModule)
    }
}


val SecondModule : State = state {

    onEntry {
        furhat.ask("The second part of establishing the therapeutic alliance involves getting the patient " +
                "to declare their will to do therapy. It is important to keep in mind that one should never explore" +
                " a problem unless the patient first declares their wish to work on it in a therapeutic setting. In this " +
                "module we will look at defenses that a patient may use to avoid expressing " +
                "their will to do therapy. Your task is to identify and block five defenses in order to get the patient " +
                "to declare their will to explore their problem with you. Are you ready?")
    }

    onResponse<Continue> {
        goto(DeclareWill)
    }

    onResponse<Repeat> {
        goto(SecondModule1)
    }
}




val DeclareWill : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> goto(Projection2)
            1 -> goto(HypotheticalSpeech2)
            2 -> goto(Defiance2)
            3 -> goto(Rumination2)
            4 -> goto(Anxiety2)
        }
    }
}


var counter2 = 0


val Counter2 : State = state {

    onEntry {
        counter2 += 1
        if (counter2 < 5)
            goto(DeclareWill)
        else {
            furhat.say(" Yes, I do want to work on my issues about getting angry with my father.")
            goto(Resolution2)
        }
    }
}
val Projection2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask("Well my brother thinks I should work on the problem, he's getting tired of it")
            1 -> furhat.ask("You really seem like you want to dig deeper into these issues")
            2 -> furhat.ask("Everyone in my family expect me to work on this problem, I guess it's hurting them")
            3 -> furhat.ask(" My doctor wants me to work on the problem because he's worried about my health")
            4 -> furhat.ask(" Do you think I should work on my problem?")
        }
    }

    onResponse<ProjectionBlock2> {

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
            0 -> furhat.say("Yes, this is a classic example of projection in which the patient projects their" +
                    "will to do therapy onto someone else. Their brother wants them to go to therapy" +
                    "even though the patient himself does not claim to want to")
            1 -> furhat.say("Good job. As in other cases of projection it is rather common for patients to" +
                    "project their will to do therapy onto the therapist. One way to block this is by reminding the patient" +
                    "that after all it was their decision to come to therapy")
            2 -> furhat.say(" Great. When blocking projection defenses it is helpful to first make the patient aware of" +
                    "what they are doing, and asking them about their own feelings without reference to others.")
            3 -> furhat.say("Perfect. In this example one can identify the projection because the patient talks about" +
                    " another person, their doctor, instead of mentioning their own emotional state")
            4 -> furhat.say(" Excellent. An effective way of blocking projection defenses is to bypass the reference" +
                    "to others and simply ask the patient what they want. Does the patient not want to feel better " +
                    "for their own sake?")

    }
        goto(Counter2)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again")
        goto(DeclareWill)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was hypothetical speech")
        goto(DeclareWill)
    }
}

val HypotheticalSpeech2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I suppose digging deeper into my feelings could be of benefit")
            1 -> furhat.ask(" Perhaps discussing my problems would help me in dealing with them")
            2 -> furhat.ask(" I guess being in therapy could be helpful for my mental health")
            3 -> furhat.ask(" Maybe investigating what is going on could potentially be good")
            4 -> furhat.ask(" In theory it could conceivably improve my situation")
        }
    }

    onResponse<HypotheticalSpeechBlock2> {

        it.intent.avoid
        it.intent.will
        it.intent.indirect
        it.intent.notice
        it.intent.problem
        it.intent.deny
        it.intent.specify
        it.intent.feel


        when (num) {
            0 -> furhat.say("Correct. Hypothetical speech may be difficult to detect since the patient does in a way" +
                    "declare their will to do therapy, only they do it in theory without really committing. It is important" +
                    "that the patient decisively makes a stand that they want to work on their problem.")
            1 -> furhat.say("That's right. In hypothetical speech the patient avoids making a firm commitment" +
                    "to their will to do therapy by couching it as a potential rather than definite action.")
            2 -> furhat.say("Great job. One can typically identify indirect speech by words phrases such as" +
                    "'I guess', 'Maybe', 'I suppose', and the like.")
            3 -> furhat.say(" Yes very good. When blocking indirect speech defenses, as with many others, it is" +
                    "often effective to simply make the patient explicitly aware of what they are doing.")
            4 -> furhat.say(" In this case the wording 'in theory' and 'conceivably' clearly give away that the " +
                    "patient is using hypothetical speech as a defense mechanism. Make this explicit and invite them" +
                    "to make a more decisive stand")
        }
        goto(Counter2)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again")
        goto(DeclareWill) }

    onResponse<GiveAnswer> {
        furhat.say(" That was hypothetical speech")
        goto(DeclareWill)
    }
}

val Defiance2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask("I don't think I want to work on my problem after all, it's not causing a lot " +
                    "of harm anyways ")
            1 -> furhat.ask(" On closer thought the problem is not as important as I let on, I don't think we need " +
                    "to go into it. ")
            2 -> furhat.ask(" I just don't want to deal with it ok")
            3 -> furhat.ask(" Why would I want to work on something that's not a problem. My life is overall fine")
            4 -> furhat.ask(" I don't want to examine it closer since there really is not much to discover anyway.")
        }
    }

    onResponse<DefianceBlock2> {

        it.intent.feel
        it.intent.deny
        it.intent.notice
        it.intent.specific
        it.intent.avoid
        it.intent.problem

        furhat.say("Yes that was defiance")
        goto(Counter2)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again")
        goto(DeclareWill)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was defiance")
        goto(DeclareWill)
    }
}


val Rumination2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I do want to look into the problem. Or you know, part of me does and the other not, " +
                    "and then I get kind of weird about it")
            1 -> furhat.ask(" I don't think it's beneficial to look into my problem more because really it's " +
                    "about my brain chemistry, at least that's what my doctor said, so how could therapy help that")
            2 -> furhat.ask(" In a way I wish I could solve this part of my inner life, but at the same time " +
                    "it's a part of myself so it just gets really complex and strange thinking about it")
            3 -> furhat.ask(" I do wish I could get better, at least I wonder what it would feel like to " +
                    "be more balanced, but it is a bit scary also")
            4 -> furhat.ask(" What do you mean by declaring my will to do therapy. I mean, I am here after all " +
                    "so in a way I am already doing therapy am I not")
        }
    }

    onResponse<RuminationBlock2> {

        it.intent.ruminate
        it.intent.feel
        it.intent.notice
        it.intent.specify
        it.intent.intellect

        when (num) {
            0-> furhat.say(" Yes this is rumination. One can identify it because the patient gets confused and " +
                    "starts talking in circles to avoid the concrete issue at hand.")
            1 -> furhat.say(" Correct. ")
        }

        furhat.say("Yes that was rumination")
        goto(Counter2)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again")
        goto(DeclareWill)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was rumination")
        goto(DeclareWill)
    }
}


val Anxiety2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I do want to look at it, I just started feeling really uncomfortable right now")
            1 -> furhat.ask(" There is this strange feeling in my stomach that is coming up")
            2 -> furhat.ask(" Do you have a tylenol? I got a headache out of nowhere")
            3 -> furhat.ask(" I don't know why but I just started feeling really cold and sweaty all of a sudden")
            4 -> furhat.ask(" I do want to explore my feeling, it's just my heart started beating really fast" +
                    "so it's difficult")
        }
    }

    onResponse<AnxietyBlock2> {
        furhat.say("Yes I do feel anxious now that you mention it")
        goto(Counter2)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again")
        goto(DeclareWill)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was anxiety")
        goto(DeclareWill)
    }
}


val Resolution2 : State = state {

    onEntry {
        furhat.ask("Great, you got through the second part! Would you like to continue to the next section")
    }

    onResponse<Continue> {
        goto(DeclareSpecific)
    }

    onResponse<No> {
        goto(Idle)
    }
}


val DeclareSpecific : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> goto(Generalization3)
            1 -> goto(NoMemory3)
            2 -> goto(Diversification3)
            else -> goto(Idle)
        }
    }
}



var counter3 = 0

val Counter3 : State = state {

    onEntry {
        counter3 += 1
        if (counter3 < 5)
            goto(DeclareSpecific)
        else {
            furhat.say(" There was a talk last weekend when I got angry with my dad right after he mentioned my" +
                    "mother's illness.")
            goto(Resolution3)
        }
    }
}



val Generalization3 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" It's the same pattern every time we talk on the phone")
            1 -> furhat.ask(" I just feel overall frustrated whenever it happens and don't know what to do")
            2 -> furhat.ask(" It's just this sense of annoyance and it keeps coming up in many instances")
            3 -> furhat.ask(" I can't give a specific example because it's a bigger issue, I think it's got" +
                    "to do with my overall character")
            4 -> furhat.ask(" I have this tendency in those situations to flare up instead of reacting calmly")
        }
    }

    onResponse<GeneralizationBlock3> {

        it.intent.feel
        it.intent.avoid
        it.intent.person
        it.intent.general
        it.intent.notice
        it.intent.specific
        it.intent.problem
        it.intent.specific

        furhat.say("Yes that was generalization")
        goto(Counter3)
    }

    onResponse<Yes> {
        furhat.say(" Let's try again")
        goto(DeclareSpecific)
    }

    onResponse<No> {
        furhat.say(" That was Generalization")
        goto(DeclareSpecific)
    }
}


val NoMemory3 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" I can't really remember a particular time it happened")
            1 -> furhat.ask(" How am I supposed to remember all the details that happen in my life")
            2 -> furhat.ask(" Thinking about it I can't really recall a specific episode when I got angry")
            3 -> furhat.ask(" I just have a vague sense of what happened, it's not very clear in my memory")
            4 -> furhat.ask(" I just know it happened sometime last weekend but can not remember more than that")
        }
    }

    onResponse<NoMemoryBlock3> {

        it.intent.general
        it.intent.avoid
        it.intent.memory
        it.intent.notice
        it.intent.problem
        it.intent.specific

        furhat.say(" Yes, that was no memory")
        goto(Counter3)
    }


    onResponse<Yes> {
        furhat.say(" Let's try again")
        goto(DeclareSpecific)
    }

    onResponse<No> {
        furhat.say(" That was no memory")
        goto(DeclareSpecific)
    }
}



val Diversification3 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask(" Another thing that is bothering me is how the rest of the family backs up my dad")
            1 -> furhat.ask(" I just want to mention first how crazy the mall was yesterday with all those sales")
            2 -> furhat.ask(" Did I tell you about the traffic on my way here. That's why I was a bit late")
            3 -> furhat.ask(" Truth be told my dad is overall a good guy. This one time he really helped me out" +
                    "when I was in financial trouble")
            4 -> furhat.ask(" You make me feel like I do when my wife complains I don't do the dishes. Though" +
                    "she does have a point sometimes.")
        }
    }
}


val Resolution3 : State = state {

    onEntry {
        furhat.say("Great, you got through the third part! Would you like to continue to the next section")
    }
}




/* onResponse<Yes> {
     goto(DeclareSpecific)
 }

 onResponse<No> {
     goto(Idle)
 } */
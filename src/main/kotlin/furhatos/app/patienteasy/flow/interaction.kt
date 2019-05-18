package furhatos.app.patienteasy.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.patienteasy.nlu.*
import org.apache.logging.log4j.core.async.JCToolsBlockingQueueFactory
import org.netlib.util.Second
import java.util.*

val Start : State = state(Interaction) {

    onEntry {

        furhat.ask("Hello and welcome to this program, in which you will learn more about Intensive Short-Term " +
                "Dynamic Psychotherapy. The first step in the therapeutic process, is to establish a conscious " +
                "therapeutic alliance. The following program will guide you through different modules that train " +
                "the skills necessary in establishing this alliance. When you are ready to start the first module, " +
                " say start.")

    }

    onResponse<Continue>{
        goto(FirstModule)
    }

    onResponse<No> {
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
        furhat.say("The first step in establishing a conscious therapeutic alliance, is to get the patient to " +
                "declare an internal emotional problem, meaning they explicitly state an issue they are struggling" +
                " with. It is important for both patient and therapist to have a clear " +
                " picture of the issue to start therapy. Patients are often reluctant to explicitly state their " +
                "issue immediately, and use various defense mechanisms to avoid talking about the problem. ")
        furhat.say("The defense mechanisms can be divided into different categories depending on their structure. " +
                " In this module you will learn to" +
                " identify different kinds of defenses, which is the first step in learning to deal with them appropriately.  " +
                "The module is complete once you have correctly identified five defenses, resulting in the " +
                "patient clearly stating their problem. ")
        furhat.ask("Say continue if you are ready to start, or say repeat if you would like to hear the " +
                "instructions again")
    }



    onResponse<Continue> {
        furhat.say("Ok, this will be the first defense.")
        goto(DeclareProblem)
    }

    onResponse<Repeat> {
        goto(FirstModule1)
    }
}

val DeclareWait : State = state {
    onTime(delay=1500) {
        goto(DeclareProblem)
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

    onTime(delay=1500) {
        furhat.ask("When you are ready for the next defense, say next")
    }

    onResponse<Continue> {
        goto(Counter1)
    }
}

val Vague1Goto : State = state {
    onEntry {
        goto(Vague1)
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
            2 -> furhat.say("Yes. Vague defenses can often be identified because the patient does not provide any " +
                    "specific details about their problem. They paint with a wide brush without providing " +
                    "much information.")
            3 -> furhat.say("Exactly right. Expressions like “something” or “stuff going on” are indicators that " +
                    "the patient is being vague about expressing their problem.")
            4 -> furhat.say("Good job. Instead of settling for the vague statement “some issues have been coming up”, " +
                    "it is helpful to block the vagueness by asking the patient to be more specific about this.")

        }

        goto(Wait1)
    }

    onResponse<Hint> {
        furhat.say("Note that the patient is not giving you very specific information about their problem. " +
                " It is difficult to work with a problem that is not clearly expressed.")
        furhat.say("The following defense will be of the same type, see if you can spot a similarity.")
        goto(Vague1Goto)
    }

    onResponse<TryAgain> {
        furhat.say("Let's try another defense")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("It was a vague defense. They can often be identified because they don't give a clear or" +
                " specific explanation of a problem, but rather ambiguous and unclear references.  ")
        furhat.say(" Let's go to another defense")
        goto(DeclareWait)
    }

    onResponse<DenialBlock1> {
        furhat.say(" Good guess. Though the patient is in a way denying you access to their emotions, denial implies" +
                " a more direct opposition to sharing their feelings. The next defense will be of the same category")
        goto(Vague1Goto)
    }

    onResponse<ProjectionBlock1> {
        furhat.say(" Not quite. Keep in mind that just because a patient mentions another person it does not necessarily " +
                " mean they are projecting. There may be other more distinct defense mechanisms at play. See if you can" +
                " spot it in the next defense of the same category.")
        goto(Vague1Goto)
    }

    onResponse<CoverWordBlock1> {
        furhat.say(" Not so. Consider what word you thought was a cover. Generally cover words stick out from the context" +
                " or when a patient is clearly more emotional than the words they use.")
        furhat.say(" Try another defense from the same category")
        goto(Vague1Goto)
    }

    onResponse<RationalizationBlock1> {
        furhat.say(" Not really. In this case the patient is not providing reasons for what they are feeling," +
                " which is the basis of rationalization. See if you can see the principle at play in the next defense " +
                " of the same kind")
        goto(Vague1Goto)
    }

}



val Denial1Goto : State = state {
    onEntry {
        goto(Denial1)
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
            2 -> furhat.say("Exactly right. In this case it is helpful to focus in on the “minor thing” to " +
                    "see if they are denying its importance and significance in their life.")
            3 -> furhat.say("Good job. Denial can take the shape of a patient denying the stimulus of a problem, " +
                    "such as a conflict with a parent, or by denying the emotional distress itself.")
            4 -> furhat.say("Excellent. This kind of denial is often referred to as minimization, in which the " +
                    "patient acknowledges they have a problem but downplays its significance and impact.")
        }


        goto(Wait1)
    }

    onResponse<Hint> {
        furhat.say("What do you notice about the patients relationship to their issue? Are they able to accept" +
                " that they have a significant problem in the first place?")
        furhat.say("Keep these questions in mind when listening to the next defense of the same kind ")
        goto(Denial1Goto)
    }

    onResponse<TryAgain> {
        furhat.say("Let's try again with another defense")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("That was a denial. You can often identify it because the patient refuses to talk about their " +
                " problem. Either they don't want to mention what is causing it, or the troubling emotion itself. ")
        furhat.say( "Now let's go to the next defense")
        goto(DeclareWait)
    }

    onResponse<VagueBlock1> {
        furhat.say(" Good guess, the patient is indeed not specific about their issue. However, there is another  " +
                " way they are avoiding talking about the problem. See if you can catch it in the following similar defense")
        goto(Denial1Goto)
    }

    onResponse<ProjectionBlock1> {
        furhat.say(" Not really. The patient is not referencing others in their defense so it's not a question of" +
                " projection.")
        goto(Denial1Goto)
    }

    onResponse<CoverWordBlock1> {
        furhat.say(" Nice try. The patient does use rather weak language and downplays the importance of their emotions. " +
                " See if you can identify another defense mechanism that fits even better for this particular scenario")
        goto(Denial1Goto)
    }

    onResponse<RationalizationBlock1> {
        furhat.say(" Not bad. It may seem like the patient is rationalizing because they are dancing around the issue " +
                " a bit. However, rationalization is when the patient provides reasons for their emotions rather than " +
                " the feelings themselves, which the patient is not doing here. Try again with the next similar defense")
        goto(Denial1Goto)
    }
}


val Projection1Goto : State = state {
    onEntry {
        goto(Projection1)
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
                    "problem, or ignore their issue by claiming that only other people believe there is a problem.")
            1 -> furhat.say("Perfect. As with other defenses, one way of blocking projection is to simply point out the " +
                    "defense explicitly to the patient. Ask them how they think without reference to others.")
            2 -> furhat.say(" Yes that's projection. As is shown in this example, it is not uncommon for patients " +
                    "to project on to the therapist. The patient does not think he has a problem, only the therapist does.")
            3 -> furhat.say("Good job. Projection can often be identified when patients make reference to other people " +
                    "without going into their own emotional state")
            4 -> furhat.say(" Exactly right. This example shows an example of how the patient projects his problem " +
                    "as in fact being his fathers issue. ")
        }

        goto(Wait1)
    }

    onResponse<Hint> {
        furhat.say(" Notice whether the patient is taking responsibility for their issue. Are there other people" +
                "involved in the patient's description?")
        furhat.say("See in the next defense of the same type you can catch in what way the patient is avoiding " +
                "their own problem")
        goto(Projection1Goto)
    }

    onResponse<TryAgain> {
        furhat.say("No problem. Let's try another defense instead.")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("That was a projection defense. Whenever the patient mentions other people and not their own feelings" +
                " it is a good sign that they may be projecting.")
        furhat.say(" Let's try another defense. ")
        goto(DeclareWait)
    }

    onResponse<VagueBlock1> {
        furhat.say(" Not quite. The patient is actually being rather clear with what they think in this case. In " +
                " the next defense, listen for the underlying reason the patient is not providing more specific information")
        goto(Projection1Goto)
    }

    onResponse<DenialBlock1> {
        furhat.say(" Good guess. It is true the patient is denying having a problem, but what is critical is the way" +
                " in which they are rejecting their issue. See if you can catch it in the next defense of a similar kind.")
        goto(Projection1Goto)
    }

    onResponse<CoverWordBlock1> {
        furhat.say(" Not really. Give it another try in the following defense of the same kind.")
        goto(Projection1Goto)
    }

    onResponse<RationalizationBlock1> {
        furhat.say(" This is a very good guess and correct in a sense. The patient is indeed providing reasons " +
                "instead of feelings. Dig deeper into what reasons they are describing and see if you spot another principle" +
                " at play in the following defense ")
        goto(Projection1Goto)
    }



}

val CoverWord1Goto : State = state {
    onEntry {
        goto(CoverWord1)
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
            0 -> furhat.say(" Yes this is a cover word defense. Cover words are words of weaker emotional " +
                    "import than the patient is actually feeling.")
            1 -> furhat.say(" Correct. 'Kind of annoying' is a cover word in this case. Clearly the patient " +
                    "must be feeling something stronger since they are unlikely to seek therapy for slight annoyance.")
            2 -> furhat.say(" Cover word is exactly right. Blocking cover words can be done by illuminating the " +
                    "particular word to the patient and inviting them to share more of their emotions")
            3 -> furhat.say(" Good job, cover words can often be identified because they don't give weight enough to the " +
                    "situation or seem contrived. The word disconcerted used in this example really sticks out.")
            4 -> furhat.say(" Like other defenses, cover words are used to distance the patient from their " +
                    "actual painful emotions. By inviting them to use more appropriate words they may feel more " +
                    "comfortable exploring their feelings and vice versa.")
        }
        goto(Wait1)
    }

    onResponse<Hint> {
        furhat.say("Pay attention to the terminology of the patient. Does he use phrases that are a bit unusual " +
                "or somehow seem out of place?")
        furhat.say("A similar kind of defense will follow soon, see if you can see the pattern")
        goto(CoverWord1Goto)
    }


    onResponse<TryAgain> {
        furhat.say("Ok, this one was pretty hard. I will give you another defense for now.")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("That was a cover word block. When identifying this defense, look for words that express weak" +
                " emotional content. Oftentimes patients may use unusual adjectives as covers so that's a good hint.")
        furhat.say(" Prepare for the next defense coming up.")
        goto(DeclareWait)
    }

    onResponse<VagueBlock1> {
        furhat.say(" Good guess, the patient is indeed vague about their issue. However they are avoiding their " +
                "problem using a very specific defense. See if you can identify it in the next defense")
        goto(CoverWord1Goto)
    }

    onResponse<DenialBlock1> {
        furhat.say(" Not really. The patient is not denying that there is something troubling them so it is " +
                " not a question of denial in this case. Try again with the next defense")
        goto(CoverWord1Goto)
    }

    onResponse<ProjectionBlock1> {
        furhat.say(" Good idea. The patient does mention other people which hints at projection. While the patient " +
                " may be projecting they are also avoiding their emotions in a very particular way in this scenario. Try" +
                " to spot it in the next similar defense")
        goto(CoverWord1Goto)
    }

    onResponse<RationalizationBlock1> {
        furhat.say(" Sensible guess. The patient does reason a bit about the cause of their emotions, which is a " +
                " sign of rationalization. There is something more specific at play in this case however. Try again" +
                " in the next defense of the same category.")
        goto(CoverWord1Goto)
    }
}


val Rationalization1Goto : State = state {
    onEntry {
        goto(Rationalization1)
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
            0 -> furhat.say(" Yes correct. Rationalization means the patient describes reasons for their " +
                    "problem rather than the experience of their emotions")
            1 -> furhat.say("Yes this is rationalization. By analyzing the underlying reasons for their " +
                    "feeling rather than exploring the emotions directly, the patient is distancing himself from " +
                    "the problem.")
            2 -> furhat.say(" Excellent. Rationalization can often be identified by words like 'think', 'reason' " +
                    " or 'because' so they are a good hint the patient is rationalizing.")
            3 -> furhat.say(" Exactly right. Keep in mind that even if the patients analysis of the problem " +
                    "is reasonable or correct, they are nevertheless distancing themselves from their emotions")
            4 -> furhat.say(" Good job. In order to block rationalization, help the patient differentiate " +
                    "their reasons from feelings and then help them explore the feelings directly")
        }
        goto(Wait1)
    }

    onResponse<Hint> {
        furhat.say(" Does the patient talk directly about their feelings? See what they talk about instead of exploring" +
                "their emotions.")
        furhat.say(" The next defense will be of the same kind. Be attentive to what the patient does instead of" +
                " sharing their feelings")
        goto(Rationalization1Goto)
    }

    onResponse<TryAgain> {
        furhat.say("Sure. Let's go with another defense, I'm sure you will get this one.")
        goto(DeclareProblem)
    }

    onResponse<GiveAnswer> {
        furhat.say("It was rationalization. Whenever patients provide reasons for their problem rather than" +
                " talking directly about their emotions they may be rationalizing. ")
        furhat.say(" Try again with the next defense coming up")
        goto(DeclareWait)
    }

    onResponse<VagueBlock1> {
        furhat.say(" A reasonable guess. The patient is not very specific about their issue which is indeed vague. " +
                " However, in what way are they avoiding being direct about there issue? See if you can identify it " +
                " in the next defense")
        goto(Rationalization1Goto)
    }

    onResponse<DenialBlock1> {
        furhat.say(" Not really. Notice the patient is rather open about having an issue. This indicates is's likely " +
                " not a question of denial. Try again with the next defense of the same category.")
        goto(Rationalization1Goto)
    }

    onResponse<ProjectionBlock1> {
        furhat.say(" Not quite. There may be some hints of projection since the patient is deflecting from their problem " +
                "onto other matters. Try to spot exactly what the patient is doing instead of describing their feelings " +
                " in this next similar defense.")
        goto(Rationalization1Goto)
    }

    onResponse<CoverWordBlock1> {
        furhat.say(" Not really. Cover word suggests the patient is using weaker emotional words to hide their " +
                " more intense feelings. This is not really the case here, give it another try.")
        goto(Rationalization1Goto)
    }
}


val Resolution1 : State = state {

    onEntry {
        furhat.ask(" Great job! You got the patient to declare an internal problem which, is the anger they " +
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

val SecondModuleGoto : State = state {
    onEntry {
        goto(SecondModule)
    }
}


val SecondModule : State = state {

    onEntry {
        furhat.say("Welcome to this module, in which you will learn more about the second part of establishing a " +
                " conscious therapeutic alliance. This step involves getting the patient to declare their will to do therapy. " +
                "It is important to keep in mind that one should never explore a problem unless the patient first expresses " +
                "their wish to work on it in a therapeutic setting.")
        furhat.say("In this module we will look at defenses that a patient may use to avoid expressing " +
                "their will to do therapy. Additionally you will encounter a new type of response, that of anxiety. " +
                " For now, if the patient experiences anxiety, simply label it as you have other defenses. In later modules you will" +
                " learn more specific skills for how to deal with it appropriately.")
        furhat.ask(" Your task is to identify and block five responses in order to get the patient to explicitly " +
                " declare their desire to do therapy." +
                " Say start if you are ready or say repeat if you would like to to hear the instructions again.")
    }

    onResponse<Continue> {
        goto(DeclareWill)
    }

    onResponse<Repeat> {
        goto(SecondModuleGoto)
    }
}



val Wait2 : State = state {

    onTime(delay=1500) {
        furhat.ask("When you are ready for the next defense, say next")
    }

    onResponse<Continue> {
        goto(Counter2)
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

val Projection2Goto : State = state {
    onEntry {
        goto(Projection2)
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
        goto(Wait2)
    }

    onResponse<Hint> {
        furhat.say(" Consider what the patient is talking about to avoid their emotions. What kind of excuse are " +
                " they making instead of diving into the issue?")
        goto(Projection2Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try another defense.")
        goto(Wait2)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was projection, they are projecting their will to do therapy onto someone else. " +
                "Notice how the patient brings up another person as wanting them to do" +
                " therapy instead of committing by their own free will.")
        goto(Wait2)
    }

    onResponse<HypotheticalSpeechBlock2> {
        furhat.say(" Not quite. The patient is in a way distancing from commitment, but in this case they are not" +
                " doing it by hypothesizing. See if you can discern the specific way in which they avoid commitment " +
                " in the next similar defense.")
        goto(Projection2Goto)
    }

    onResponse<DefianceBlock2> {
        furhat.say(" Not so. Defiance implies a more direct refusal of participating in the therapeutic process. " +
                " Try again with another defense.")
        goto(Projection2Goto)

    }

    onResponse<RuminationBlock2> {
        furhat.say(" No. In rumination the patient talks around in circles, while in this case the patient communicates" +
                " rather clearly. Try again in the next defense of the same category.")
        goto(Projection2Goto)
    }

    onResponse<AnxietyBlock2> {
        furhat.say(" Incorrect. Keep in mind anxiety is a fundamentally different response from other defenses." +
                " It often involves some uncomfortable bodily sensations on the part of the patient.")
        goto(Projection2Goto)
    }
}


val HypotheticalSpeech2Goto : State = state {
    onEntry {
        goto(HypotheticalSpeech2)
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
            0 -> furhat.say("Correct. Hypothetical speech may be difficult to detect since the patient does in a way " +
                    "declare their will to do therapy, only they do it in theory without really committing. It is important " +
                    "that the patient decisively makes a stand that they want to work on their problem.")
            1 -> furhat.say("That's right. In hypothetical speech the patient avoids making a firm commitment " +
                    "to their will to do therapy by couching it as a potential rather than definite action.")
            2 -> furhat.say("Great job. One can typically identify indirect speech by words phrases such as " +
                    "'I guess', 'Maybe', 'I suppose', and the like.")
            3 -> furhat.say(" Yes very good. When blocking indirect speech defenses, as with many others, it is " +
                    "often effective to simply make the patient explicitly aware of what they are doing.")
            4 -> furhat.say(" In this case the wording 'in theory' and 'conceivably' clearly give away that the " +
                    "patient is using hypothetical speech as a defense mechanism. Make this explicit and invite them" +
                    "to make a more decisive stand")
        }
        goto(Wait2)
    }

    onResponse<Hint> {
        furhat.say(" Be attentive to the wording of the patient. Are they committing their intention to do" +
                " therapy strongly and clearly?")
        goto(Projection2Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again with another defense.")
        goto(Wait2) }

    onResponse<GiveAnswer> {
        furhat.say(" That was hypothetical speech. Notice how the patient talked in hypothetical and ambiguous terms in " +
                " order to avoid a firm commitment.")
        goto(Wait2)
    }

    onResponse<ProjectionBlock2> {
        furhat.say(" Not really. Since the patient is not bringing up other people in their defense, it is likely" +
                " not a case of projecting. Try another defense of the same category")
        goto(HypotheticalSpeech2Goto)
    }

    onResponse<DefianceBlock2> {
        furhat.say(" Incorrect. The patient is actually being quite agreeable in this situation, which suggests" +
                " they are not defying the process. Try again with the next similar defense. ")
        goto(HypotheticalSpeech2Goto)
    }

    onResponse<RuminationBlock2> {
        furhat.say(" Not quite. Though the response is rather ambiguous it is still rather short and concise. " +
                " Generally rumination consists of longer and more incoherent responses. See if you can get it in the" +
                " next defense of the same type.")
        goto(HypotheticalSpeech2Goto)
    }

    onResponse<AnxietyBlock2> {
        furhat.say(" Not correct. The patient is not disclosing any signs of bodily discomfort, which is the" +
                " primary signal of anxiety. Try another similar defense.")
        goto(HypotheticalSpeech2Goto)
    }
}

val Defiance2Goto : State = state {
    onEntry {
        goto(Defiance2)
    }
}

val Defiance2 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.ask("I don't think I want to work on my problem after all, it's not causing a lot " +
                    "of harm anyways ")
            1 -> furhat.ask(" On closer thought the problem is not as important as I let on, I don't want  " +
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

        when (num) {
            0 -> furhat.say(" Yes correct. When patients refuse to cooperate in the therapeutic process " +
                    " this way it is called defiance.")
            1 -> furhat.say("That's right. This is very similar to denial, only the patient is more directly also refusing " +
                    " to cooperate with the therapist.")
            2 -> furhat.say(" Correct. This particularly aggressive type of denial, in which the patient flat" +
                    "  out refuses to establish an alliance, is called defiance.")
            3 -> furhat.say(" Yes exactly. Similar to denial, defiance also implies the patient is actively opposing" +
                    " himself to continuing with the process.")
            4 -> furhat.say(" That's exactly right. Like with other defenses, when dealing with defiance it is" +
                    " helpful make the patient aware of their response and ask if they really want to keep opposing" +
                    " the process which would be helpful to them.")

        }

        furhat.say("Yes that was defiance")
        goto(Wait2)
    }

    onResponse<Hint> {
        furhat.say(" Is the patient being cooperative? Notice the  ")
        goto(Defiance2Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try another defense.")
        goto(Wait2)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was defiance. Notice its similarity to denial and how the patient is actively refusing" +
                " to cooperate in the therapeutic process by declaring they don't want to declare their will. ")
        goto(DeclareWill)
    }

    onResponse<DenialBlock1> {
        furhat.say(" Very good guess. The patient really is denying, but notice how the patient is directly " +
                " opposing himself to the therapist and refusing to cooperate in the therapeutic process. See if you can " +
                " find an even more accurate label in the following defense of the same category. ")
        goto(Defiance2Goto)
    }

    onResponse<ProjectionBlock2> {
        furhat.say(" Not so. Since the patient is not mentioning other people in their defense, they are not projecting" +
                " their will to do therapy onto someone else. Try again in this next similar defense")
        goto(Defiance2Goto)
    }

    onResponse<HypotheticalSpeechBlock2> {
        furhat.say(" Not really. Hypothetical speech implies the patient is being indirect or ambiguous in their way" +
                " of speaking. In this case the patient is expressing himself very clearly and directly. Try again in" +
                " another defense of the same category.")
        goto(Defiance2Goto)
    }

    onResponse<RuminationBlock2> {
        furhat.say("Incorrect. The patient is being very concise and direct in his response, as opposed to the" +
                " incoherent rambling of rumination. Have another try in the next defense of the same type")
        goto(Defiance2Goto)
    }

    onResponse<AnxietyBlock2> {
        furhat.say(" Not quite. Anxiety is a fundamentally different response from most defenses, in that it expresses" +
                " itself through uncomfortable feelings in the body that the patient cannot handle. Try another defense" +
                " of the same kind")
        goto(Defiance2Goto)
    }
}

val Rumination2Goto : State = state {
    onEntry {
        goto(Rumination2)
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
            1 -> furhat.say(" Correct. The patient is mentioning many things, like brain chemistry and their doctor" +
                    " instead of the issue. This is a good clue they are ruminating")
            2 -> furhat.say(" Great job! As with other defenses, a good approach is to simply make the patient " +
                    " aware of their unhelpful reaction and encourage them to be more direct.")
            3 -> furhat.say(" That's right. Here the patient is expressing doubt about the process and an overall" +
                    " uncertain quality. These are signs of rumination")
            4 -> furhat.say(" Perfect. Not a super clear case of rumination, but what is important to notice is" +
                    " that the patient is being indirect and talking in circles instead of clearly committing their" +
                    " will to do therapy. ")
        }

        goto(Wait2)
    }

    onResponse<Hint> {
        furhat.say(" Does the patient make sense? Are they being clear and direct in their communication?")
        goto(Rumination2Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again with another defense. I'm sure you will get this one")
        goto(DeclareWill)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was rumination. Notice how the patient rants in a rather incoherent way that is difficult " +
                "to make sense of. This is generally a good sign of rumination.")
        goto(Wait2)
    }

    onResponse<VagueBlock1> {
        furhat.say(" Very close. The answer is certainly vague. What makes this type of defense unique is how" +
                " the patient is not even talking about the issue but is rather talking around it in circles. See" +
                " if you can figure it out in the next defense of the same type. ")
    }

    onResponse<ProjectionBlock2> {
        furhat.say(" Not quite. The patient may bring up other people, but this is an effect of the generally confusing" +
                " character of their defense. See if you can see the larger pattern in the next similar defense.")
        goto(Rumination2Goto)
    }

    onResponse<HypotheticalSpeechBlock2> {
        furhat.say(" Very good guess. The patient is indeed being indirect about their commitment. This particular " +
                " defense has another important characteristic to it, relating to its length and incoherence. See if you " +
                "can catch it with this next defense of the same category.")
        goto(Rumination2Goto)
    }

    onResponse<DefianceBlock2> {
        furhat.say(" Not quite. The patient is willing to partake in the therapeutic process, they are just no being" +
                " clear and direct about it. Defiance implies they are outright refusing to cooperate. Try again" +
                " with another defense of the same kind. ")
    }

    onResponse<AnxietyBlock2> {
        furhat.say(" Not so. The incoherent character of their defense may suggest the patient is " +
                " experiencing anxiety. Even though this is the case, one generally regulates anxiety when the patient" +
                " expresses or shows signs of bodily discomfort they cannot deal with. Try again with another similar" +
                " defense.")
        goto(Rumination2Goto)
    }
}

val Anxiety2Goto : State = state {
    onEntry {
        goto(Anxiety2)
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
            4 -> furhat.ask(" I do want to explore my feeling, it's just my heart started beating really fast " +
                    "so it's difficult")
        }
    }

    onResponse<AnxietyBlock2> {

        when (num) {
            0 -> furhat.say("Yes correct. If the patient mentions feeling weird or uncomfortable this is a sign" +
                    " they are experiencing anxiety.")
            1 -> furhat.say(" Good job. Anxiety is a rather different reaction than defenses since it mainly" +
                    " involves bodily sensations that are unpleasant for the patient")
            2 -> furhat.say(" Great. Headaches are another symptom of a rise in anxiety. In general, if the " +
                    " patient is referring to any bodily discomfort this should be a good clue there is an onset of" +
                    " anxiety.")
            3 -> furhat.say(" Perfect. When patients experience anxiety, it is important to regulate it before" +
                    " moving on to working on and blocking other defenses.")
            4 -> furhat.say(" That's right. The bodily symptoms of the patients give away that they have anxiety. " +
                    " As you get more experience, you may even be able to notice anxiety in the atient by being observant " +
                    " of their body language and movements")
            }
            goto(Wait2)
        }

    onResponse<Hint> {
        furhat.say(" Does this strike you as different than most defenses encountered so far? Notice what the patient" +
                " is talking about and see what it may be a symptom of.")
        goto(Anxiety2Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Let's try again with another defense.")
        goto(DeclareWill)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was anxiety. Notice the different character of this response, in which the patient directly" +
                " refers to uncomfortable feelings in the body.")
        goto(Wait2)
    }

    onResponse<ProjectionBlock2> {
        furhat.say(" Not so. The patient is not bringing up other people in their response, so it is not a case" +
                " of projection. Have a go at another similar defense.")
        goto(Anxiety2Goto)
    }

    onResponse<HypotheticalSpeechBlock2> {
        furhat.say(" Incorrect. The patient is not being indirect in this case, in fact they are being quite " +
                " clear about what they are experiencing. Try again with another defense of the same type. ")
        goto(Anxiety2Goto)
    }

    onResponse<DefianceBlock2> {
        furhat.say(" Not quite. The patient may seem like they are defying because they refuse to declare " +
                " their will to do therapy. However, look closely at what exactly is preventing them from expressing their" +
                " desire to get better. Try this in this next defense of the same category. ")
        goto(Anxiety2Goto)
    }

    onResponse<RuminationBlock2> {
        furhat.say(" Not really. It is true that the patient is avoiding declaring their will to do therapy by " +
                " bringing up something else. But they are being rather clear about what is preventing them from" +
                " doing this. See if you can catch it in the next defense of the same kind. ")
        goto(Anxiety2Goto)
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



val Wait3 : State = state {

    onTime(delay=1500) {
        furhat.ask("When you are ready for the next defense, say next")
    }

    onResponse<Continue> {
        goto(Counter2)
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
            furhat.say(" There was a talk last weekend when I got angry with my dad right after he mentioned my " +
                    "mother's illness.")
            goto(Resolution3)
        }
    }
}



val Generalization3Goto : State = state {
    onEntry {
        goto(Generalization3)
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
            3 -> furhat.ask(" I can't give a specific example because it's a bigger issue, I think it's got " +
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

        when (num) {
            0 -> furhat.say(" Yes that's correct. Notice how the patient is referring to an overall 'pattern' rather," +
                    " than specifying a particular instance in which this pattern took place. ")
            1 -> furhat.say(" Perfect. The patient does mention their feelings of frustration, which is good. " +
                    " However, they talk about the feeling of frustration in a general sense rather than describing an " +
                    " event that made them frustrated ")
            2 -> furhat.say(" Great job, the patient is indeed being general. The phrase 'in many instances'  is " +
                    " very telling that the patient is talking about a general trend rather than a specific situation")
            3 -> furhat.say(" That's right. Another way patients can be general is by talking about their overall " +
                    "personality or character instead of sharing a specific example when their problem showed up")
            4 -> furhat.say(" Yes, this is generalization. Notice how the patient offers some valuable insight " +
                    " about their situation, but none the less fails to re-tell a particular scenario, in which they " +
                    " experienced emotional difficulty. ")
        }

            goto(Counter3)
    }



    onResponse<NoMemoryBlock3> {
        furhat.say(" Not quite. Lack of memory defenses are generally more explicit in that patients directly state " +
                " they cannot remember. This is not really the case here, try again in the next defense of the same kind.")
        goto(Generalization3Goto)
    }

    onResponse<DiversificationBlock3> {
        furhat.say(" Good guess. It certainly seems like the patient is trying to change the topic in order to avoid " +
                " providing a specific example. They are still, however, talking about the issue at large. See if you can" +
                " spot the correct defense in this next example of the same category. ")
        goto(Generalization3Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Ok, let's try another defense. ")
        goto(Wait3)
    }

    onResponse<GiveAnswer> {
        furhat.say(" That was generalization. Notice how the patient is presenting a large or general account of the" +
                " issue, using words like 'pattern', 'usually', or 'overall', instead of providing a specific instance of " +
                " their problem")
        goto(Wait3)
    }
}

val NoMemory3Goto : State = state {
    onEntry {
        goto(NoMemory3)
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

        when (num) {
            0 -> furhat.say(" That's right. This is a rather explicit example of no memory since the patient clearly" +
                    " states they cannot remember a particular instance of what is being asked about")
            1 -> furhat.say(" Perfect. Even though the patient is not explicitly stating they cannot remember, they " +
                    " are still hiding behind a poor memory excuse to avoid answering the question directly. ")
            2 -> furhat.say(" Great job. A rather evident example of no memory considering the wording, 'I can't really" +
                    " recall'. One way to deal with this is to encourage the patient to see if, after all, they can't " +
                    " remember something important. ")
            3 -> furhat.say(" Exactly so. It may be tempting to accept no memory defenses since it is possible for" +
                    " people to simply forget. It is, however, rather unlikely that patients have no memory of such " +
                    " emotionally salient events. ")
            4 -> furhat.say(" That's correct. When dealing with no memory defenses it is wise to keep in mind that the" +
                    " patient may genuinely not remember certain details. However, if they consistently 'cannot " +
                    " remember' significant events, it may be a sign they are using it as a defense mechanism.")
        }

        goto(Counter3)
    }


    onResponse<GeneralizationBlock3> {
        furhat.say(" Not quite. Generalization implies the patient is giving large and non-specific information. In " +
                " this case the patient is also rather vague, but notice why specifically they are not providing a more " +
                " detailed response. Try this in the next similar response")
        goto(NoMemory3Goto)
    }

    onResponse<DiversificationBlock3> {
        furhat.say(" Not really. The patient is perhaps slightly changing the topic, but really there is a more " +
                " direct reason why they are not sharing a specific example of their problem. See if you can spot it " +
                " in the next defense of the same kind.")
        goto(NoMemory3Goto)
    }

    onResponse<TryAgain> {
        furhat.say("Ok, let's try another defense")
        goto(Wait3)
    }

    onResponse<GiveAnswer> {
        furhat.say(" This is a case of a no memory response. Notice how the patient avoids describing an instance " +
                "of their problem by saying they cannot remember one way or another. Of course patients may genuinely " +
                " not remember certain things, but if they consistently cannot recall emotionally important events, it" +
                " may be a hint they are using it as a defense mechanism.")
        goto(DeclareSpecific)
    }
}

val Diversification3Goto : State = state {

    onEntry {
        goto(Diversification3)
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
            3 -> furhat.ask(" Truth be told my dad is overall a good guy. This one time he really helped me out " +
                    "when I was in financial trouble")
            4 -> furhat.ask(" You make me feel like I do when my wife complains I don't do the dishes. Though " +
                    "she does have a point sometimes.")
        }
    }

    onResponse<DiversificationBlock3> {

        when (num) {
            0 -> furhat.say(" That's correct. Especially when the patient is changing the topic to something seemingly " +
                    " related, as in this case, it is important to not get off track and immediately block the defense.")
            1 -> furhat.say( "This is indeed diversification. Notice how the patient brings in a completely unrelated " +
                    " topic in order to avoid sharing a specific example")
            2 -> furhat.say(" Great job. In diversification, the patient attempts to avoid their feelings by " +
                    " changing the topic. Block this by refocusing attention on the issue, at hand and avoid being " +
                    " dragged into the story. ")
            3 -> furhat.say(" Perfect. The patient is avoiding describing a specific instance of the problem by " +
                    " telling an unrelated story about their father. It is fine to acknowledge this, but make sure to " +
                    " get the process back on track without lingering on diverse topics.")
            4 -> furhat.say(" That's right. Whenever the patient talks about something seemingly unrelated, such " +
                    " as his wife and dishes, this is a good clue he is using diversification as a defense mechanism.")
        }

        goto(Counter3)

    }

    onResponse<GeneralizationBlock3> {
        furhat.say(" Good guess. Generalization implies the patient is talking about the issue at hand, just that he is  " +
                " not being specific enough. Notice in the next defense if the patient is still talking about the same " +
                " topic at all.")
        goto(Diversification3Goto)
    }

    onResponse<NoMemoryBlock3> {
        furhat.say(" Not quite. No memory defense suggests the person is seemingly struggling with remembering " +
                " a specific instance. This was not the case in this defense, see if you can spot the correct one in " +
                " the next defense of the same kind")
    }

    onResponse<AvoidanceBlock3> {
        furhat.say(" Very good guess. The patient is certainly avoiding the topic by not providing a specific example" +
                " of their problem. In avoidance, however, the patient is consciously aware of avoiding their emotions. " +
                " In the next defense, in what way is the patient avoiding the therapeutic process?")
    }

    onResponse<TryAgain> {
        furhat.say(" Ok, let's try another defense")
        goto(Wait3)
    }

    onResponse<GiveAnswer> {
        furhat.say(" This defense is diversification, which means the patient changes topics in order to avoid " +
                " answering the question. You can usually tell it's diversification if the patient suddenly introduces " +
                " something unrelated into the conversation")
    }


}


val Avoidance3Goto : State = state {
    onEntry {
        goto(Avoidance3)
    }
}


val Avoidance3 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.say(" I really don't want to think about that, it's very painful")
            1 -> furhat.say(" I haven't thought of any incidents for a long time, and don't intend to think about " +
                    " it now or again.")
            2 -> furhat.say("You are asking me to dive into my feelings? That's not what I want, I thought therapy " +
                    "was supposed to make me happy and not go into negative emotions.")
            3 -> furhat.say(" I'd really prefer not to think about it, it causes so many negative feelings that " +
                    " I try to avoid.")
            4 -> furhat.say(" I have been doing a good job of avoiding these thoughts and feelings, and don't see " +
                    " a reason to go into them again.")
        }
    }

    onResponse<AvoidanceBlock3> {

        it.intent.avoid
        it.intent.feel

        when (num) {
            0 -> furhat.say(" That's right. The patient admits they prefer not to think about their painful feelings  " +
                    " which is a case of avoidance. However, the way to progress through therapy is to explore theses " +
                    " feelings even if they are temporarily painful")
            1 -> furhat.say(" Correct. When patients openly express that they are unwilling to explore their feelings, " +
                    " it is a good sign they are in a state of avoidance.")
            2 -> furhat.say(" Great job. When blocking avoidance, it is good to explain to patients that " +
                    " it is necessary to overcome their fear of exploring painful feelings in order to get better. ")
            3 -> furhat.say(" Exactly right. The patient even uses the word 'avoid' in this case. In general with" +
                    " avoidance, it is helpful to provide a safe environment in which they feel brave enough to explore" +
                    " feelings they usually avoid")
            4 -> furhat.say(" Perfect. In fact, all defenses are a type of avoidance. What signifies this defense is that " +
                    " patients openly admit to, and are consciously aware of their avoiding behavior. ")
        }

        goto(Counter3)
    }

    onResponse<GeneralizationBlock3> {
        furhat.say("Not quite. When patients generalize, they still talk about their issue but do so in an indirect " +
                " and general sense. In this example the patient completely evades the topic, try again in the next defense" +
                " in the same category.")
        goto(Avoidance3Goto)
    }

    onResponse<NoMemoryBlock3> {
        furhat.say(" Good guess. The patient is indeed failing to provide a concrete instance of their issue, but " +
                " in this case they are not blaming their lack of memory. See if you can spot the reason they don't " +
                " declare a specific example in the next defense of the same kind")
        goto(Avoidance3Goto)
    }

    onResponse<DiversificationBlock3> {
        furhat.say(" Not bad, but not quite right. In diversification the patient completely changes the topic " +
                " in order to confront their emotions. In this case the patient does evade the topic, but they are being " +
                " more direct about how they do it. Try again in this next defense")
        goto(Avoidance3Goto)
    }

    onResponse<TryAgain> {
        furhat.say(" Ok, let's try another defense")
        goto(Wait3)
    }

    onResponse<GiveAnswer> {
        furhat.say(" This defense is avoidance. Though all defenses are ultimately a form of avoidance, in these cases" +
                " the patients are consciously aware that they are avoiding their problem. It is helpful to remind them" +
                " how this strategy has not served them in the past, and that they need to confront their emotions in " +
                " order to improve. ")
    }
}



val Intellectualization3 : State = state {

    val rand = Random()
    val num = rand.nextInt(5)

    onEntry {
        when (num) {
            0 -> furhat.say(" Last time it happened, I remember thinking how we really need to find a solution" +
                    " to this problem")
            1 -> furhat.say(" Sometimes when it happens, I try to use techniques from books about how to calm down, " +
                    " but it does not really work in the moment, you know. ")
            2 -> furhat.say(" Maybe if I had more emotional intelligence I would be able to handle those types" +
                    " of situations better, but I suppose I have to deal with what I have.")
            3 -> furhat.say( " It happened one time last weekend, and I remember being really on edge that day. And" +
                    " when I'm on edge I am less able to deal with confrontation. So there is that.")
            4 -> furhat.say(" A specific situation you say? How is that supposed to help? I mean, I have read " +
                    " that I need to deal with my childhood issues in order to solve problems like these.")
        }

    }

    onResponse<IntellecutalizationBlock3> {
        when (num) {
            0 -> furhat.say(" Great job. In intellectualization, the patient offers thoughts instead of feelings. " +
                    " Instead of describing the situation or their emotions, they share their thoughts on the matter.")
            1 -> furhat.say(" Perfect. In this case the patient starts talking about their thoughts and strategies" +
                    " of dealing with the situation, instead of simply describing the situation and their feelings about it.")
            2 -> furhat.say(" That's correct. The word 'if' is generally a good sign the patient is intellectualizing, " +
                    " since it is commonly followed by some sort of hypothetical thought pattern. ")
            3 -> furhat.say (" That's right. When blocking intellectualization, don't go along with the thoughts the " +
                    " patients offer. Gently make them aware of their defense and redirect their attention to what the " +
                    " theraputic process demands")
            4 -> furhat.say(" Indeed it is intellectualization. In this case, the patient is thinking about the " +
                    " grander scheme of therapy instead of the specific problem. However, they are still offering thoughts" +
                    " instead of feelings, which is the hallmark of intellectualization.")
        }
        goto(Counter3)
    }

    onResponse<RationalizationBlock1> {
        furhat.say(" Great guess. Rationalization and intellectualization are sometimes used interchangeably and " +
                " are very easy to confuse. Technically, in rationalization, patients attempt to justify their actions " +
                " rather than talk about how they felt. In intellectualization, patients focus on the rational side of " +
                " the problem instead of the emotion, offering thoughts instead of emotions. This subtle difference will" +
                " be explored in more detail in future modules")
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
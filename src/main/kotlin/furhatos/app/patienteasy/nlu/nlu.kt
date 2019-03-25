package furhatos.app.patienteasy.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language


class Inquiry : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what problem can I help you with",
                "what problems are you experiencing",
                "how can I help you",
                "what difficulties are you experiencing",
                "why have you come here today to get my help"
        )
    }
}


class Person : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("mother", "mom", "father", "dad", "brother", "sister", "young brother", "old brother",
                "young sister", "old sister", "grandmother", "grandfather", "boss", "co-worker", "colleague",
                "he", "she", "person", "family member", "child", "kid", "son", "daughter", "you", "your",
                "me", "we", "us", "I", "I'm", "I am", "own")
    }
}

class NegativeFeeling : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("anger", "angry", "rage", "raging", "sad", "sadness", "sorrow", "melancholy",
                "frustrated", "frustration", "annoyed", "annoying", "anxious,", "anxiety", "shame",
                "ashamed", "bitter", "bitterness", "cold", "coldness", "empty", "emptiness", "depressed",
                "depressing", "disconnected", "disconnection", "disgust", "disgusted", "conflicted",
                "confused", "unhappy", "unhappiness", "obsessed", "obsessive", "worry", "worrisome",
                "worried", "frustrated", "frustrates", "frustrated", "stress", "stressed",
                "tired")
    }
}

class CoverWords : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("irritated", "irritation", "annoy", "annoyed", "annoying", "annoyance", "down",
                "upset", "upsetting", "agitated", "agitation", "agitate", "distress", "distressing",
                "distressed", "perturbed", "perturbing", "shock", "shocked", "feel lost", "dismayed",
                "dismay", "word", "words", "cover", "cover word", "disconcerted", "disconcerting")
    }
}


class Denying : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("deny", "denying", "withhold", "withholding", "repressing",
                "repress", "suppress", "suppressing", "negating", "negate",
                "ignore", "ignoring", "not seeing", "refuse", "refusing", "hide", "hiding", "minimize")
    }
}

class Feel : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("feel", "feeling", "felt", "feelings", "emotion", "emotions,", "impressions",
                "impression", "sensation", "mental state", "mental states", "experience",
                "experiencing", "sensing", "sense", "inner experience")
    }
}

class Intellectual : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("idea", "ideas", "thought", "think", "thinking", "intellectual", "sentence",
                "word", "rational", "rationalize", "analyze", "analyzing", "analyzed", "analysis",
                "explain", "explanation", "rationalizing", "intellectualize", "intellectualizing")
    }
}

class Avoid : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("avoid", "avoiding", "avoided", "evading", "evade", "evaded", "vague", "vagueness",
                "avert", "averted", "averting", "dodge", "dodging", "dodged", "escape", "escaped", "escaping")
    }
}



class Specify : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("specific", "specify", "particular", "exact", "deliberate", "give example",
                "precise", "definite", "definitive", "explicit", "clear", "clearly", "distinct",
                "concrete", "example", "examples", "expand", "explain", "explained", "describe",
                "described", "tell more", "tell me more", "explain more", "tell me", "mention", "mentioned")
    }
}


class Ruminate : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("ruminate", "ruminating", "rumination", "ruminated", "describe", "describing",
                "description", "telling", "re-telling", "explaining", "explain", "explained",
                "story", "narrative", "explanation", "a story", "word")
    }
}


class Notice : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("realize", "realized", "understand", "understood", "notice", "noticed",
                "see", "seen", "note", "understanding", "figure", "figured", "insight")
    }
}

class Generalize : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("general", "generalize", "generalization", "generalized", "vague",
                " opaque", "unclear", "indistinct", "imprecise", "unspecific", "obscure",
                " inexact", "fuzzy", "hazy", "ambiguous", "equivocal")
    }
}


class Memory : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf(" memory", "memories", "recollection", "recollect", "recollected",
                "remember", "remembered", "remembered", "reminisce", "reminisced", "reminiscence",
                "remind", "reminded", "come to mind", "have memory", "bring to mind",
                "recall", "recalled")
    }
}

class Problem : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("incident", "episode", "occurence", "happening", "event", "circumstance", "matter",
                "occasion", "problem", "issue", "dispute", "trouble", "obstacle", "complication", "dilemma",
                "fault", "wrong", "wrongdoing", "doing", "instance", "incident", "example")
    }
}

class Obey : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("listen", "obey", "submit", "conform", "give in", "accept", "adhere to", "comply",
                "heed", "observe", "surrender", "accede", "accord", "acquiesce", "agree", "assent", "concur",
                "submit", "fulfill", "follow", "serve", "influence")
    }
}


class Force : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("force", "compel", "command", "demand", "pressure", "oblige", "order", "drive", "drag",
                "impose", "inflict", "press", "require", "urge", "coerce", "cause", "constrain", "insist",
                "impel", "necessitate", "pressurize")
    }
}


class Will : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("will", "desire", "intention", "mind", "choice", "choice", "determination", "inclination",
                "attitude", "power", "resolution", "resolve", "wish", "aim", "conviction", "option", "discretion",
                " preference", "purpose", "prerogative"," self-control", "urge", "volition", "willpower",
                "wishes", "yearning", "fancy", "self-discipline", "self-restraint", "willfulness", "want")
    }
}


class Indirect : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("hypothetical", "hypothetically", "suppose", "maybe", "guess", "I guess", "perhaps", "potentially",
                "theoretically", "conceivably", "presumably", "probably", "supposedly", "in a sense", "in theory",
                "likely", "sort of", "indirect")
    }
}



class Continue : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Continue",
                "Go on",
                "Let's continue",
                "Yes",
                "Ready",
                "Start")
    }
}

class Repeat : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Repeat",
                "Repeat that please",
                "Could you repeat that",
                "Say again",
                "Again",
                "No")
    }
}

class TryAgain : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Try again",
                "Yes",
                "Again",
                "Next",
                "Another",
                "Let's try another",
                "Let's try again",
                "Let me try another",
                "Let's go next")
    }
}

class GiveAnswer : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Give answer",
                "Answer",
                "No",
                "Give up",
                "I give up",
                "What is the answer",
                " Give me the answer",
                "Could you give me the answer")
    }
}


class VagueBlock1(val specific : Specify? = null, val avoid : Avoid? = null,
                  val notice : Notice? = null, val negative : NegativeFeeling? = null,
                  val person : Person? = null, val problem:  Problem? = null,
                  val feel : Feel? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Could you be more @specific?",
                " That's a bit @avoid",
                " That's not very clear",
                " What else can you tell me?",
                " Can you tell me more? ",
                " Do you @notice you are being @avoid?",
                " You need to be more @specific",
                " You are not saying very much",
                " It sounds like there is more to it",
                " Can you give an example?",
                " What should we do about @avoid?",
                " Do you @notice you are @avoid my question?",
                " Do you @notice you are @avoid?",
                " What @negative you?",
                " Why do you get @negative",
                " When do you get @negative",
                " How come you get @negative",
                " What about your @person",
                " What does @person do?",
                " What does your @person do?",
                " What is it about @person",
                " What about the weird @feel",
                " When do you get weird @feel",
                " Can you @specific the @feel",
                " Can you @specific the weird @feel",
                " Can you @specific the @negative @feel",
                " What about the incident?",
                " What incident?",
                " Can you @specific the incident?",
                " What incident was it?",
                " Can you @specific about the incident?",
                " What thing one time?",
                " Can you @specific about the thing?",
                " Can you @specific about the thing?",
                " That's a bit @avoid, could you be @specific",
                " That's @avoid, could you be more @specific",
                " What kind of @problem",
                " Can you @specific @problem",
                " Could you @specific the @problem",
                " Would you @specific the kinds of @problem",
                " How does @person make you @feel",
                " How come @person does that",
                " What @problem does @person",
                " You @avoid @specific @person",
                " @notice you @avoid @specific @negative",
                " @feel @negative is @avoid",
                " @negative is @avoid, could you @specific",
                " @notice you @avoid instead of @specific @feel",
                " Vagueness",
                " Vague"
        )
    }
}

class DenialBlock1(val deny : Denying? = null, val feel : Feel? = null, val notice : Notice? = null,
                   val specific : Specify? = null, val avoid: Avoid? = null,
                   val problem: Problem? = null) :  Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" Still you are @deny your feelings",
                " It's ok to feel @feel, no need to @deny it",
                " Do you @notice you are @deny what you @feel?",
                " You really don't think it's important how you @feel?",
                " Why do you say it's nothing?",
                " Why do you say your @feel is nothing?",
                " Why do you think it's not important?",
                " It sounds like it's important",
                " It sounds like your @feel is important",
                " It sounds like it's something",
                " You don't have to @deny your @feel",
                " Do you notice you are @deny?",
                " How come you @deny your @feel?",
                " Do you @notice you @deny your @feel",
                " Can you @notice you @deny",
                " How can we work if you @deny",
                " How can you improve if you @deny",
                " How can you improve if you @deny your @feel",
                " You are here after all",
                " You are @deny your @feel",
                " @deny your @feel got you here",
                " @deny your @feel got you here in the first place",
                " @deny your @feel is what got you here",
                " If you want to improve you can't keep @deny",
                " You keep @deny your @feel",
                " Instead of @deny could you @feel",
                " @notice you @deny your @feel",
                " @notice you @deny @problem",
                " why do you @deny @problem",
                " Why @deny your @feel",
                " can you tell me your @problem and not @deny",
                " can you can you tell me @feel instead of @avoid",
                " can you @specific @feel instead of @deny,",
                " Would you @specific instead of @deny",
                " @notice you @deny your @feel",
                " do you @notice you @deny instead of @specific",
                " Denial",
                " Deny"
        )
    }
}

class RationalizationBlock1(val intellect : Intellectual? = null, val feel : Feel? = null,
                            val notice : Notice? = null, val avoid: Avoid? = null,
                            val person : Person? = null, val negative: NegativeFeeling? = null,
                            val problem: Problem? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("You are only telling me your @intellect",
                " That was a @intellect, but what do you @feel?",
                " That was a @intellect",
                " Do you really think that's the case?",
                " @notice again you are @avoid talking about your @feel",
                " @notice you are @intellect",
                " Do you @notice you @intellect",
                " Can you @notice you @intellect",
                " That's just @intellect, not your @feel",
                " You keep @intellect instead of @feel",
                " You just @intellect, what are your @feel",
                " Are these @intellect really your @feel",
                " What does the @intellect mean?",
                " Now you are @intellect",
                " You are just @intellect",
                " That's @intellect",
                " That's just @intellect",
                " What does @person do to push your buttons",
                " What does @person do",
                " How does @person do that",
                " How does your @person do that",
                " Is it really @person fault?",
                " Why does @person affect you",
                " How does @person affect you so much",
                " Is it really the coffee?",
                " How can it be coffee?",
                " Do you really think it's your diet?",
                " Sounds like @intellect",
                " You are @avoid your @feel",
                " You use @intellect to @avoid",
                " You @intellect to @avoid @feel",
                " Do you @notice you @intellect to @avoid",
                " Can you @notice you @intellect to @avoid @feel",
                " @notice you @intellect to @avoid",
                " Those are just @intellect, how about your @feel",
                " That's @intellect, but what about @feel",
                " His voice is just @intellect",
                " Voice is just @intellect, can you tell me @feel",
                " voice is @intellect, what about @feel",
                " voice is @intellect, how about your @feel",
                " @problem is @intellect",
                " @problem is @intellect, how about @feel",
                " @problem is @intellect, what about your @feel",
                " being stressed is @intellect, what about your @feel",
                " @negative is @intellect, how about your @feel",
                " @negative is @intellect, what about @feel",
                " being @negative is @intellect, how about @feel",
                " being @negative is @intellect, what about your @feel",
                " @notice @negative is @intellect",
                " @notice @negative is just @intellect, tell me @feel",
                " do you @notice @negative is @intellect",
                " can you @notice @negative is @intellect, can you tell me @feel",
                " rationalization",
                " thought"
        )
    }
}

class CoverWordBlock1(val cover : CoverWords? = null, val negative : NegativeFeeling? = null,
                      val feel : Feel? = null, val notice : Notice? = null,
                      val specify : Specify? = null, val person : Person? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" That's just a @cover, it doesn't tell me how you actually @feel",
                " That's just a @cover",
                " It doesn't @specify how you @feel",
                " What do you mean by @cover? Does it really describe how you @feel?",
                " What do you mean by @cover?",
                " Does it really describe how you @feel?",
                " It seems you @feel are stronger than you admit",
                " Do you not also @feel something stronger?",
                " It seems you @feel more than @cover",
                " Don't you have stronger @feel?",
                " Don't you @feel more than that?",
                " Do you have more @feel than @cover?",
                " You are helpless to tell me you @feel",
                " What is that? What do you mean?",
                " How come when you're @negative you say you're @cover?",
                " You're just telling me @cover, is there more @feel?",
                " @negative sounds like a @cover",
                " @cover sounds like a @cover",
                " @notice you @cover your @feel",
                " @notice you @cover",
                " Do you @notice you @cover",
                " Can you @notice you @cover",
                " Can you @notice you @cover your @feel",
                " It's important you @notice how you @cover",
                " @cover does not @specify your @feel",
                " That @cover does not @specify",
                " That @cover does not @specify your @feel",
                " Does @cover @specify your @feel",
                " Is there more to @cover",
                " Do you @feel more than @cover",
                " Do you @feel more than @cover @specify",
                " Can you @feel more than @cover",
                " Can you @specify @cover",
                " What does @person do",
                " How does @person do that",
                " Why does @person affect you so",
                " How come @person affects you",
                " How come @person does that",
                " That's a @cover but how do you @feel",
                " Can you tell me @feel instead of @cover",
                " That's a @cover how do you @feel",
                " That's a @cover for your @feel",
                " @notice you @cover",
                " @notice you @cover your @feel",
                " @negative is @cover how do you @feel",
                " @cover is @cover how do you @feel",
                " What is your @feel when @person does that",
                " What do you actually @feel",
                " Cover word",
                " Cover word block"
        )
    }
}


class ProjectionBlock1(val person : Person? = null, val obey : Obey? = null, val intellect: Intellectual? = null,
                       val will : Will? = null, val force : Force? = null, val specify : Specify? = null,
                       val problem : Problem? = null, val avoid : Avoid? = null,
                       val feel: Feel? = null, val notice : Notice? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" Is it really @person @problem",
                " How come you @obey @person",
                " Why @obey @person",
                " How come @person @obey",
                " Why @person @obey",
                " Is @person right",
                " Don't @person have @person own will",
                " Is it really @person @problem",
                " Don't @person make @person decisions",
                " Don't @person make @person own decisions",
                " Did @person really do that",
                " What do @person @intellect",
                " @person can only explore what @person @intellect",
                " @person can only explore what @person @intellect is ok",
                " @person can only explore what @person accepts",
                " @person is here for @person",
                " @person is here for @person sake",
                " Did @person really @force @person",
                " How did @person @force @person",
                " How come @person @force",
                " How come @person @force @person",
                " @notice @person project",
                " do you @notice @person project unto @person",
                " can you @notice @person project",
                " can you @notice @person are projecting",
                " Don't @person have @person own @will",
                " What about @person own @will",
                " What about @person @will",
                " how about @person @will",
                " @specify @person @will",
                " could @person @specify @person @will",
                " @notice @person don't @specify @person @will",
                " do you @notice @person don't @specify @will",
                " @notice @person @avoid @specify @person @will",
                " can @person @specify @person @will",
                " what do @person @will",
                " how come it's @person @problem",
                " is it really @person @problem",
                " that's @person @feel what about @person @feel",
                " that it @person @intellect what about @person @person @feel",
                " @notice that's @person @intellect how about @person @person @intellect",
                " that is @person @feel what about @person @will",
                " @notice that is @person @intellect how about @person @person @will",
                " that is @person @feel can @person @specify @person @will",
                " that's @person @intellect instead of @person @intellect",
                " that's @person @feel and not @person @will",
                " can @person @specify your @will instead of @person @intellect",
                " could @person @specify your @feel instead of @person @intellect",
                " could @person @specify your @intellect instead of @person @will",
                "Projection",
                " you are projecting")
    }
}



class DefianceBlock2(val deny : Denying? = null, val feel : Feel? = null, val notice : Notice? = null,
                     val specific : Specify? = null, val avoid: Avoid? = null,
                     val problem: Problem? = null) :  Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" Still you are @deny your feelings",
                " It's ok to feel @feel, no need to @deny it",
                " Do you @notice you are @deny what you @feel?",
                " You really don't think it's important how you @feel?",
                " Why do you say it's nothing?",
                " Why do you say your @feel is nothing?",
                " Why do you think it's not important?",
                " It sounds like it's important",
                " It sounds like your @feel is important",
                " It sounds like it's something",
                " You don't have to @deny your @feel",
                " Do you notice you are @deny?",
                " How come you @deny your @feel?",
                " Do you @notice you @deny your @feel",
                " Can you @notice you @deny",
                " How can we work if you @deny",
                " How can you improve if you @deny",
                " How can you improve if you @deny your @feel",
                " You are here after all",
                " You are @deny your @feel",
                " @deny your @feel got you here",
                " @deny your @feel got you here in the first place",
                " @deny your @feel is what got you here",
                " If you want to improve you can't keep @deny",
                " You keep @deny your @feel",
                " Instead of @deny could you @feel",
                " @notice you @deny your @feel",
                " @notice you @deny @problem",
                " why do you @deny @problem",
                " Why @deny your @feel",
                " can you tell me your @problem and not @deny",
                " can you can you tell me @feel instead of @avoid",
                " can you @specific @feel instead of @deny,",
                " Would you @specific instead of @deny",
                " @notice you @deny your @feel",
                " do you @notice you @deny instead of @specific",
                " defiance"
        )
    }
}


class HypotheticalSpeechBlock2(val avoid: Avoid? = null, val problem: Problem? = null,
                               val indirect: Indirect? = null, val will : Will? = null,
                               val specify : Specify? = null, val deny : Denying? = null,
                               val notice : Notice? = null, val feel : Feel? = null) :  Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" @indirect is @indirect",
                " @notice you are @indirect",
                " @notice you are being @indirect",
                " @indirect will not help",
                " Instead of @indirect tell me your @will",
                " could you @specify @will instead of @indirect",
                " Instead of @indirect can you @specify @will",
                " we need @specify instead of @indirect",
                " @indirect @avoid the @problem",
                " @indirect @deny @problem",
                " @indirect @avoid @problem we need @specify @will",
                " @indirect @deny the @problem could you @specify @will",
                " @notice @indirect is not @specify",
                " do you @notice you are @indirect",
                " @indirect is not your @will",
                " @indirect @avoid @will",
                " @indirect @avoid @feel",
                " now you are @indirect",
                " now you are being @indirect",
                " hypothetica speech",
                " indirect speech",
                "hypothetical",
                "indirect")
    }
}


class RuminationBlock2(val ruminate : Ruminate? = null, val feel : Feel? = null, val notice : Notice? = null,
                       val specify : Specify? = null, val intellect: Intellectual? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" You are @ruminate",
                " Do you @notice you are @ruminate",
                " @ruminate won't get you anywhere",
                " @ruminate does not explain your @feel",
                " You @ruminate and not @feel",
                " Your @ruminate does not tell us about your @feel",
                " That may be true, but what about @feel",
                " @ruminate may be right, but we need to express @feel",
                " That may be true, but what do you @feel",
                " Can you tell me your @feel rather than @ruminate",
                " Now you @ruminate again",
                " Do you @notice you @ruminate",
                " You are helpless to @notice you are @ruminate",
                " Can you @notice @feel instead of @ruminate",
                " Can you @specify instead of @ruminate",
                " @specify instead of @ruminate",
                " Please be more @specify",
                " Can you be more @specify",
                " Can you @specify @feel instead of @ruminate",
                " @specify @feel instead of @ruminate",
                " @ruminate does not help you @feel",
                " @ruminate does not help you @specify @feel",
                " @ruminate does not help @specify your @feel",
                " We need to @specify your @feel",
                " You need to @specify your @feel",
                " Try @specify @feel instead",
                " You tell me @ruminate instead of @feel",
                " You @intellect instead of @feel",
                " You tell me @intellect instead of @feel",
                " You just @intellect",
                " Can you @specify @feel instead of @intellect",
                " What do you @feel though",
                " @intellect will not help you",
                " That's a nice @intellect, but what about @feel",
                " Interesting @intellect, but can you @specify @feel",
                " rumination",
                " you are ruminating"
        )
    }
}



class ProjectionBlock2(val person : Person? = null, val obey : Obey? = null, val intellect: Intellectual? = null,
                       val will : Will? = null, val force : Force? = null, val specify : Specify? = null,
                       val problem : Problem? = null, val avoid : Avoid? = null,
                       val feel: Feel? = null, val notice : Notice? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" Is it really @person @problem",
                " How come you @obey @person",
                " Why @obey @person",
                " How come @person @obey",
                " Why @person @obey",
                " Is @person right",
                " Don't @person have @person own will",
                " Is it really @person @problem",
                " Don't @person make @person decisions",
                " Don't @person make @person own decisions",
                " Did @person really do that",
                " What do @person @intellect",
                " @person can only explore what @person @intellect",
                " @person can only explore what @person @intellect is ok",
                " @person can only explore what @person accepts",
                " @person is here for @person",
                " @person is here for @person sake",
                " Did @person really @force @person",
                " How did @person @force @person",
                " How come @person @force",
                " How come @person @force @person",
                " @notice @person project",
                " do you @notice @person project unto @person",
                " can you @notice @person project",
                " can you @notice @person are projecting",
                " Don't @person have @person own @will",
                " What about @person own @will",
                " What about @person @will",
                " how about @person @will",
                " @specify @person @will",
                " could @person @specify @person @will",
                " @notice @person don't @specify @person @will",
                " do you @notice @person don't @specify @will",
                " @notice @person @avoid @specify @person @will",
                " can @person @specify @person @will",
                " what do @person @will",
                " how come it's @person @problem",
                " is it really @person @problem",
                " that's @person @feel what about @person @feel",
                " that it @person @intellect what about @person @person @feel",
                " @notice that's @person @intellect how about @person @person @intellect",
                " that is @person @feel what about @person @will",
                " @notice that is @person @intellect how about @person @person @will",
                " that is @person @feel can @person @specify @person @will",
                " that's @person @intellect instead of @person @intellect",
                " that's @person @feel and not @person @will",
                " can @person @specify your @will instead of @person @intellect",
                " could @person @specify your @feel instead of @person @intellect",
                " could @person @specify your @intellect instead of @person @will",
                " projection",
                " you are projecting")
    }
}

class AnxietyBlock2 : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("That's anxiety",
                " You are experiencing anxiety",
                " Anxiety",
                " Anxiety is covering your feelings")
    }
}

class GeneralizationBlock3(val feel : Feel? = null, val specific : Specify? = null,
                           val general : Generalize? = null, val notice : Notice? = null,
                           val person : Person? = null, val problem: Problem? = null,
                           val avoid : Avoid? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("You are @general",
                " You are being @general",
                " Do you @notice you are @general",
                " @notice you are @general",
                " Could you be more @specific",
                " Instead of @general, could you @specific",
                " @general is not a @specific",
                " @general is not @feel",
                " Could you be @specific instead of @general",
                " Could you provide @specific",
                " Could you provide @specific, now you are @general",
                " Yes, but that is @general",
                " That is @general and @general",
                " We need more @specific than @general",
                " You are too @general",
                " You are too @general, it would help to be @specific",
                " How does @person do that",
                " How come @person does that,",
                " Do you @notice you @general",
                " Can you @notice you @general instead of @specific",
                " @notice you @general and not @feel",
                " Do you @notice you @general instead of @feel",
                " @notice you are not @specific @feel",
                " Could you @specific @problem",
                " Could you @specific @problem instead of @general",
                " @notice you @general your @problem",
                " @notice you are being @general about the @problem",
                " you are being @general about the @problem",
                " could you be more @specific about @problem",
                " could you give @specific",
                " we need @specific @problem",
                " we need @specific @problem instead of @general",
                " What is the pattern",
                " again you are @general",
                " again you are @general instead of @specific",
                " could you @specific @feel",
                " @general @avoid @problem",
                " @notice @general @avoid @feel",
                " @person @avoid @feel with @general",
                " @person @avoid @feel by being @general",
                " To not @avoid @feel try @specific",
                " Instead of @avoid @specific @problem",
                " Try to @specific @problem instead of @general",
                " generalization",
                " you are generalizing")
    }
}

class NoMemoryBlock3(val general : Generalize? = null, val memory : Memory? = null,
                     val specific: Specify? = null, val notice: Notice? = null,
                     val avoid: Avoid? = null, val problem: Problem? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(" How come you can't @memory",
                " Are you sure you can't @memory",
                " What about your @memory of it)",
                " How about your @memory",
                " Could you tell me more about the @memory",
                " Let's focus on @memory",
                " How come you forgot?",
                " Are you sure you forgot?",
                " Do you usually forget?",
                " Do you have more @specific @memory",
                " How come your @memory is not @specific",
                " Your @memory is very @general",
                " How come your @memory is @general",
                " @notice your @memory is @general",
                " Why do you @avoid @memory",
                " @notice you @avoid @memory",
                " do you usually have bad @memory",
                " @notice you can't @memory your @problem",
                " @notice your @memory @avoid @problem",
                " you can't @memory your @problem",
                " you can't @memory your @problem more @specific",
                " can you @specific your @problem",
                " is @memory usually @problem",
                " Just @specific one @problem",
                " @specific one @memory",
                " you must @memory one @problem",
                " you must @specific one @memory",
                " could you @specific one @problem",
                " how come you cannot @memory",
                " your @memory is @avoid",
                " @notice you @avoid @problem with @memory",
                " no memory",
                " bad memory")
    }
}

#!/usr/bin/env ruby

CONF = {
  
  # how many concurrent sessions?
  :session_count => 5000,

  # how many secons should a session last
  :session_lifetime => 600,

  # how many keywords per session?
  :session_words => (1..40)

}

WORDS = %w{
  buonanotte uno quattro cinque sette dieci arrivederci arrivederla molto scusi 
  desidera? qualche parla lentamente acqua l'aranciata nostra auguri tutto come 
  questo questa l'ombra ombrello dove britannico americano australiano francese 
  diciannove venti forse Italia America Australia Francia l'uccello prego Canada 
  alzare gabinetto teatro costruzione tramonto salato amaro serpente mettere 
  sapere cavallo tiepido trattare campo cognome quadro cornice cadere fresco   
  certamente brutto grande piccolo consegnare l'anno Mario diciassette diciotto 
  lavoro anziano chiaro domandare suonare farmacia casalinga fiamma aeroporto 
  pescheria macelleria giardino largo stretto contrario rispondere azzurro 
  canadese Spagna spagnolo Portogallo arancione ventuno ventidue ventitre l'ora 
  l'appuntamento ovest nord sud-ovest ventisei ventisette ventotto ventinove 
  abitare abito tutto tutto lavorare lavorare l'incontro quanto? quanta pagare 
  tedesco corto lungo cattivo qualche undici dodici tredici quattordici quindici 
  platino ferro rocca tuono grandine ventoso colla prato parco dentista mappa 
}


class Range 
  def size
    max-min
  end
end

class Hash
  def rand_key
    keys.sample
  end
end


class Emitter

  Sessions = Hash.new

  def self.emit!(session_id)
    puts "#{session_id};#{WORDS.sample}"
  end

  def self.next!
    if Sessions.length < CONF[:session_count]
      n = rand(CONF[:session_words].size) + CONF[:session_words].min
      Sessions[rand(8**32).to_s(36)] = n
    end

    emit!(session = Sessions.rand_key)
    Sessions.delete(session) if (Sessions[session] -= 1) < 0

    interval = CONF[:session_lifetime].to_f / CONF[:session_words].max
    sleep (interval / CONF[:session_count].to_f)
  end

end


loop{ Emitter.next! }
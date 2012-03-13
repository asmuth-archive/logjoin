#!/usr/bin/env ruby
require "socket"

if RUBY_VERSION != "1.9.3"
  puts "ruby 1.9.3 please"
  exit!
end

if ARGV.length != 2
  puts "usage: #{$0} host port"
  exit!
end


CONF = {
  
  # how many concurrent sessions?
  :session_count => 7000,

  # how many secons should a session last
  :session_lifetime => 600,

  # how many keywords per session?
  :session_words => (1..100),

  :trgt_host => ARGV[0],
  :trgt_port => ARGV[1].to_i

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


class Emitter

  def self.loop!
    instance = self.new
    loop{ instance.next! }
  end

  def initialize
    @sessions = Hash.new
    @total = Hash.new{ |h,k| h[k] = 0 }
    @socket = UDPSocket.new
    @last_print = 0
    @last_tuples = 0
  end

  def emit!(session_id)
    @total[:tuples] += 1
    socket_args = [0, CONF[:trgt_host], CONF[:trgt_port]]
    send_args = ["#{session_id};#{WORDS.sample}", socket_args]
    @socket.send(*send_args.flatten)
  end

  def next!
    new_session! if @sessions.length < CONF[:session_count]
    emit!(session = @sessions.keys.sample)
    @sessions.delete(session) if (@sessions[session] -= 1) < 0
    ((Time.now.to_i - @last_print) > 1) ? print! : sleep!
  end

private

  def new_session!
    @total[:sessions] += 1
    n = rand(CONF[:session_words].size) + CONF[:session_words].min
    @sessions[rand(8**32).to_s(36)] = n
  end

  def sleep!
    interval = CONF[:session_lifetime].to_f / CONF[:session_words].max
    sleep (interval / CONF[:session_count].to_f) unless ENV["NO_SLEEP"]
  end

  def print!
    tuples_per_sec = (
      (@total[:tuples] - @last_tuples) / 
      (Time.now - @last_print).to_f
    )
    @last_print = Time.now.to_i
    @last_tuples = @total[:tuples]
    puts [ 
      "#{@sessions.size} active sessions",
      "#{tuples_per_sec.round(1)} tuples/s",
      "#{@total[:tuples]} tuples sent in #{@total[:sessions]} sessions" 
    ].join(", ")
  end

end

Emitter.loop!
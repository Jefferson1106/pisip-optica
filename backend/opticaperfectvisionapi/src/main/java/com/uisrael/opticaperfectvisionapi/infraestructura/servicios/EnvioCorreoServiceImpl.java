package com.uisrael.opticaperfectvisionapi.infraestructura.servicios;

import java.nio.charset.StandardCharsets;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.uisrael.opticaperfectvisionapi.aplicacion.servicios.IEnvioCorreoService;

@Service
public class EnvioCorreoServiceImpl implements IEnvioCorreoService {

	private final JavaMailSender mailSender;
	private final String remitente;

	public EnvioCorreoServiceImpl(JavaMailSender mailSender,
			@Value("${spring.mail.username}") String remitente) {
		this.mailSender = mailSender;
		this.remitente = remitente;
	}

	@Override
	public void enviarContrasenia(String destinatario, String contrasenia) {
		try {
			MimeMessage mensaje = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(
					mensaje,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setFrom(remitente);
			helper.setTo(destinatario);
			helper.setSubject("Recuperación de acceso | Perfect Vision");
			helper.setText(
					"Perfect Vision\n\n"
							+ "Recuperación de acceso\n\n"
							+ "Tu contraseña registrada es: " + contrasenia + "\n\n"
							+ "Por seguridad, no compartas esta información con nadie.\n"
							+ "Si no solicitaste este correo, puedes ignorarlo.",
					construirCorreoRecuperacion(contrasenia));
			helper.addInline(
					"perfectVisionLogo",
					new ClassPathResource("mail/perfect-vision-logo.png"),
					"image/png");

			mailSender.send(mensaje);
		} catch (MailAuthenticationException ex) {
			throw new IllegalStateException(
					"No se pudo autenticar el correo saliente. Verifique el usuario y clave de aplicacion SMTP.");
		} catch (MessagingException ex) {
			throw new IllegalStateException("No fue posible preparar el correo de recuperación.", ex);
		} catch (MailException ex) {
			throw new IllegalStateException("No fue posible enviar el correo en este momento.");
		}
	}

	private String construirCorreoRecuperacion(String contrasenia) {
		String contraseniaSegura = HtmlUtils.htmlEscape(contrasenia);

		return """
				<!doctype html>
				<html lang="es">
				<head>
					<meta charset="UTF-8">
					<meta name="viewport" content="width=device-width, initial-scale=1.0">
					<title>Recuperación de acceso</title>
				</head>
				<body style="margin:0;padding:0;background-color:#f2f7fb;font-family:Arial,Helvetica,sans-serif;color:#263746;">
					<div style="display:none;max-height:0;overflow:hidden;opacity:0;">
						Consulta tus credenciales de acceso a Perfect Vision.
					</div>
					<table role="presentation" width="100%%" cellspacing="0" cellpadding="0" border="0"
							style="width:100%%;background-color:#f2f7fb;">
						<tr>
							<td align="center" style="padding:32px 12px;">
								<table role="presentation" width="600" cellspacing="0" cellpadding="0" border="0"
										style="width:100%%;max-width:600px;background-color:#ffffff;border-radius:18px;overflow:hidden;box-shadow:0 8px 28px rgba(21,83,126,.12);">
									<tr>
										<td align="center" style="padding:28px 32px 22px;background-color:#ffffff;border-top:7px solid #0b83d5;">
											<img src="cid:perfectVisionLogo" width="230" alt="Perfect Vision"
													style="display:block;width:230px;max-width:80%%;height:auto;border:0;">
										</td>
									</tr>
									<tr>
										<td style="padding:8px 44px 40px;">
											<p style="margin:0 0 8px;color:#f28b00;font-size:13px;font-weight:bold;letter-spacing:1.2px;text-align:center;text-transform:uppercase;">
												Acceso seguro
											</p>
											<h1 style="margin:0 0 16px;color:#173b57;font-size:28px;line-height:1.25;text-align:center;">
												Recuperación de acceso
											</h1>
											<p style="margin:0 0 24px;color:#536879;font-size:16px;line-height:1.6;text-align:center;">
												Recibimos una solicitud para consultar la contraseña registrada en tu cuenta de administrador.
											</p>
											<table role="presentation" width="100%%" cellspacing="0" cellpadding="0" border="0"
													style="width:100%%;background-color:#edf7fd;border:1px solid #cce9f9;border-radius:12px;">
												<tr>
													<td align="center" style="padding:22px 20px;">
														<p style="margin:0 0 8px;color:#527086;font-size:12px;font-weight:bold;letter-spacing:1px;text-transform:uppercase;">
															Tu contraseña
														</p>
														<p style="margin:0;color:#086cae;font-family:Consolas,'Courier New',monospace;font-size:24px;font-weight:bold;line-height:1.35;word-break:break-all;">
															%s
														</p>
													</td>
												</tr>
											</table>
											<table role="presentation" width="100%%" cellspacing="0" cellpadding="0" border="0"
													style="width:100%%;margin-top:24px;">
												<tr>
													<td width="4" style="width:4px;background-color:#f6a21a;border-radius:4px;"></td>
													<td style="padding:3px 0 3px 14px;color:#637786;font-size:14px;line-height:1.55;">
														Por tu seguridad, no compartas esta información. Si no solicitaste este correo, puedes ignorarlo.
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td align="center" style="padding:22px 32px;background-color:#123f5d;color:#d9eaf4;font-size:12px;line-height:1.6;">
											<strong style="color:#ffffff;">Perfect Vision</strong><br>
											Miramos a través de tus ojos<br>
											<span style="color:#9fc2d7;">Este es un mensaje automático; por favor, no respondas.</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</body>
				</html>
				""".formatted(contraseniaSegura);
	}
}
